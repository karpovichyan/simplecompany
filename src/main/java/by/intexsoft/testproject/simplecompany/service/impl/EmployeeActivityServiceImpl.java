package by.intexsoft.testproject.simplecompany.service.impl;

import by.intexsoft.testproject.simplecompany.dto.EmployeeActivityDto;
import by.intexsoft.testproject.simplecompany.entity.Activity;
import by.intexsoft.testproject.simplecompany.entity.Employee;
import by.intexsoft.testproject.simplecompany.entity.EmployeeActivity;
import by.intexsoft.testproject.simplecompany.entity.Plan;
import by.intexsoft.testproject.simplecompany.exception.ActivityNotFoundException;
import by.intexsoft.testproject.simplecompany.exception.EmployeeNotFoundException;
import by.intexsoft.testproject.simplecompany.exception.PlanNotFoundException;
import by.intexsoft.testproject.simplecompany.mapper.EmployeeActivityMapper;
import by.intexsoft.testproject.simplecompany.mapper.context.CycleAvoidingMappingContext;
import by.intexsoft.testproject.simplecompany.repository.ActivityRepository;
import by.intexsoft.testproject.simplecompany.repository.EmployeeActivityRepository;
import by.intexsoft.testproject.simplecompany.repository.EmployeeRepository;
import by.intexsoft.testproject.simplecompany.repository.PlanRepository;
import by.intexsoft.testproject.simplecompany.service.EmployeeActivityService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeActivityServiceImpl implements EmployeeActivityService {
    private final EmployeeActivityRepository employeeActivityRepository;
    private final PlanRepository planRepository;
    private final EmployeeRepository employeeRepository;
    private final ActivityRepository activityRepository;
    private final EmployeeActivityMapper employeeActivityMapper;

    public EmployeeActivityServiceImpl(
            EmployeeActivityRepository employeeActivityRepository,
            PlanRepository planRepository,
            EmployeeRepository employeeRepository,
            ActivityRepository activityRepository,
            EmployeeActivityMapper employeeActivityMapper
    ) {
        this.employeeActivityRepository = employeeActivityRepository;
        this.planRepository = planRepository;
        this.employeeRepository = employeeRepository;
        this.activityRepository = activityRepository;
        this.employeeActivityMapper = employeeActivityMapper;
    }

    @Override
    public EmployeeActivityDto create(EmployeeActivityDto employeeActivityDto) {
        Plan plan = planRepository.findById(employeeActivityDto.getPlan().getId())
                .orElseThrow(() -> new PlanNotFoundException(
                        "Plan with id = " + employeeActivityDto.getPlan().getId() + " not found!"));

        Employee employee = employeeRepository.findById(employeeActivityDto.getEmployee().getId())
                .orElseThrow(() -> new EmployeeNotFoundException(
                        "Employee with id = " + employeeActivityDto.getEmployee().getId() + " not found!"));

        Activity activity = activityRepository.findById(employeeActivityDto.getActivity().getId())
                .orElseThrow(() -> new ActivityNotFoundException(
                        "Activity with id = " + employeeActivityDto.getActivity().getId() + " not found!"));

        EmployeeActivity employeeActivity = employeeActivityMapper.toEntity(employeeActivityDto, plan, employee, activity);
        return employeeActivityMapper.toDto(employeeActivityRepository.save(employeeActivity), new CycleAvoidingMappingContext());
    }

    @Override
    public List<EmployeeActivityDto> getAll() {
        return employeeActivityRepository.findAll()
                .stream()
                .map((EmployeeActivity employeeActivity) -> employeeActivityMapper.toDto(employeeActivity, new CycleAvoidingMappingContext()))
                .collect(Collectors.toList());
    }
}
