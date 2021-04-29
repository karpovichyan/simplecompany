package by.intexsoft.testproject.simplecompany.service.impl;

import by.intexsoft.testproject.simplecompany.controller.param.PositionEmployeeRequestParam;
import by.intexsoft.testproject.simplecompany.controller.param.SalaryEmployeeRequestParam;
import by.intexsoft.testproject.simplecompany.dto.EmployeeDto;
import by.intexsoft.testproject.simplecompany.entity.*;
import by.intexsoft.testproject.simplecompany.entity.enumeration.ActivityType;
import by.intexsoft.testproject.simplecompany.exception.ContractNotFoundException;
import by.intexsoft.testproject.simplecompany.exception.EmployeeNotFoundException;
import by.intexsoft.testproject.simplecompany.exception.PlanNotFoundException;
import by.intexsoft.testproject.simplecompany.exception.PositionNotFoundException;
import by.intexsoft.testproject.simplecompany.mapper.EmployeeMapper;
import by.intexsoft.testproject.simplecompany.mapper.context.CycleAvoidingMappingContext;
import by.intexsoft.testproject.simplecompany.repository.ContractRepository;
import by.intexsoft.testproject.simplecompany.repository.EmployeeRepository;
import by.intexsoft.testproject.simplecompany.repository.PlanRepository;
import by.intexsoft.testproject.simplecompany.repository.PositionRepository;
import by.intexsoft.testproject.simplecompany.service.EmployeeService;
import by.intexsoft.testproject.simplecompany.service.specification.EmployeeSpecification;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private static final double EMPLOYEE_ABSENCE_LIMIT_PASS = 0.5;
    private final EmployeeRepository employeeRepository;
    private final ContractRepository contractRepository;
    private final PositionRepository positionRepository;
    private final EmployeeMapper employeeMapper;
    private final PlanRepository planRepository;

    public EmployeeServiceImpl(
            EmployeeRepository employeeRepository,
            ContractRepository contractRepository,
            PositionRepository positionRepository,
            EmployeeMapper employeeMapper,
            PlanRepository planRepository) {
        this.employeeRepository = employeeRepository;
        this.contractRepository = contractRepository;
        this.positionRepository = positionRepository;
        this.employeeMapper = employeeMapper;
        this.planRepository = planRepository;
    }

    @Override
    public EmployeeDto create(EmployeeDto employeeDto) {
        Position position = positionRepository.findById(employeeDto.getPosition().getId())
                .orElseThrow(() -> new PositionNotFoundException(
                        "Position with id: " + employeeDto.getPosition().getId() + " not found!"));

        Contract contract = contractRepository.findById(employeeDto.getContract().getId())
                .orElseThrow(() -> new ContractNotFoundException(
                        "Contract with id: " + employeeDto.getContract().getId() + " not found!"));

        Employee employee = employeeMapper.toEntity(employeeDto, position, contract, new CycleAvoidingMappingContext());
        return employeeMapper.toDto(employeeRepository.save(employee), new CycleAvoidingMappingContext());
    }

    @Override
    public void delete(Integer employeeId) {
        employeeRepository.deleteById(employeeId);
    }

    @Override
    public List<EmployeeDto> getByFullNameAndPositions(PositionEmployeeRequestParam positionEmployeeRequestParam) {
        Employee employeeProbe = new Employee(
                positionEmployeeRequestParam.getFirstName(),
                positionEmployeeRequestParam.getLastName(),
                new Position(positionEmployeeRequestParam.getPosition(), null),
                null
        );

        return employeeRepository.findAll(Example.of(employeeProbe))
                .stream()
                .map((Employee employee) -> employeeMapper.toDto(employee, new CycleAvoidingMappingContext()))
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDto> getByFullNameAndSalaries(SalaryEmployeeRequestParam salaryEmployeeRequestParam) {
        Specification<Employee> specification = Specification
                .where(EmployeeSpecification.whereFirstNameEqualTo(salaryEmployeeRequestParam.getFirstName())
                        .and(EmployeeSpecification.whereLastNameEqualTo(salaryEmployeeRequestParam.getLastName()))
                        .and(EmployeeSpecification.whereSalaryEqualsTo(salaryEmployeeRequestParam.getSalary())));

        return employeeRepository.findAll(specification)
                .stream()
                .map((Employee employee) -> employeeMapper.toDto(employee, new CycleAvoidingMappingContext()))
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDto> getEmployeesByAbsences(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("date should not be null");
        }
        Plan plan = planRepository.findByDate(date)
                .orElseThrow(() -> new PlanNotFoundException("Plan with date "
                        + date + " not found"));

        Set<EmployeeActivity> employeeActivities = plan.getEmployeeActivities();

        Map<Employee, List<EmployeeActivity>> employeeActivityMap = employeeActivities.stream()
                .collect(Collectors.groupingBy(EmployeeActivity::getEmployee));

        List<Employee> employee = new ArrayList<>();
        for (Map.Entry<Employee, List<EmployeeActivity>> entry : employeeActivityMap.entrySet()) {
            List<EmployeeActivity> employeeActivityList = entry.getValue();

            int presentHoursSum = 0;
            int absenceReasonHoursSum = 0;

            for (EmployeeActivity employeeActivity : employeeActivityList) {

                if (employeeActivity.getActivity().getActivityType() == ActivityType.PRESENT) {
                    presentHoursSum += employeeActivity.getHours();
                } else {
                    absenceReasonHoursSum += employeeActivity.getHours();
                }
            }

            EmployeeActivity employeeActivity = employeeActivityList.get(0);
            Integer totalHours = employeeActivity.getPlan().getTotalHours();
            int absenceHours = totalHours - presentHoursSum - absenceReasonHoursSum;

            if ((double) absenceHours / presentHoursSum >= EMPLOYEE_ABSENCE_LIMIT_PASS) {
                employee.add(employeeActivity.getEmployee());
            }
        }
        return employee
                .stream()
                .map((Employee newEmployee) -> employeeMapper.toDto(newEmployee, new CycleAvoidingMappingContext()))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public EmployeeDto update(EmployeeDto employeeDto, Integer employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with id = " + employeeId + " not found"));
        employeeMapper.updateFromDto(employeeDto, employee, new CycleAvoidingMappingContext());
        return employeeMapper.toDto(employee, new CycleAvoidingMappingContext());
    }
}
