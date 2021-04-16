package by.intexsoft.testproject.simplecompany.service.impl;

import by.intexsoft.testproject.simplecompany.controller.param.EmployeeRequestParam;
import by.intexsoft.testproject.simplecompany.dto.EmployeeDto;
import by.intexsoft.testproject.simplecompany.entity.Contract;
import by.intexsoft.testproject.simplecompany.entity.Employee;
import by.intexsoft.testproject.simplecompany.entity.Position;
import by.intexsoft.testproject.simplecompany.exception.ContractNotFoundException;
import by.intexsoft.testproject.simplecompany.exception.EmployeeNotFoundException;
import by.intexsoft.testproject.simplecompany.exception.PositionNotFoundException;
import by.intexsoft.testproject.simplecompany.mapper.EmployeeMapper;
import by.intexsoft.testproject.simplecompany.repository.ContractRepository;
import by.intexsoft.testproject.simplecompany.repository.EmployeeRepository;
import by.intexsoft.testproject.simplecompany.repository.PositionRepository;
import by.intexsoft.testproject.simplecompany.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ContractRepository contractRepository;
    private final PositionRepository positionRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeServiceImpl(
            EmployeeRepository employeeRepository,
            ContractRepository contractRepository,
            PositionRepository positionRepository,
            EmployeeMapper employeeMapper
    ) {
        this.employeeRepository = employeeRepository;
        this.contractRepository = contractRepository;
        this.positionRepository = positionRepository;
        this.employeeMapper = employeeMapper;
    }

    @Override
    public EmployeeDto create(EmployeeDto employeeDto) {
        Position position = positionRepository.findById(employeeDto.getPositionId())
                .orElseThrow(() -> new PositionNotFoundException(
                        "Position with id: " + employeeDto.getPositionId() + " not found!"));

        Contract contract = contractRepository.findById(employeeDto.getContractId())
                .orElseThrow(() -> new ContractNotFoundException(
                        "Contract with id: " + employeeDto.getContractId() + " not found!"));

        Employee employee = employeeMapper.toEntity(employeeDto, position, contract);
        return employeeMapper.toDto(employeeRepository.save(employee));
    }

    @Override
    public List<EmployeeDto> get(EmployeeRequestParam getEmployeeRequestParam) {
        boolean isFirstName = getEmployeeRequestParam.getFirstName() != null
                && getEmployeeRequestParam.getLastName() == null
                && getEmployeeRequestParam.getPosition() == null;
        if (isFirstName) {
            return employeeRepository.findAllByFirstName(getEmployeeRequestParam.getFirstName())
                    .stream()
                    .map(employeeMapper::toDto)
                    .collect(Collectors.toList());
        }

        boolean isLastName = getEmployeeRequestParam.getFirstName() == null
                && getEmployeeRequestParam.getLastName() != null
                && getEmployeeRequestParam.getPosition() == null;
        if (isLastName) {
            return employeeRepository.findAllByLastName(getEmployeeRequestParam.getLastName())
                    .stream()
                    .map(employeeMapper::toDto)
                    .collect(Collectors.toList());
        }

        boolean isPosition = getEmployeeRequestParam.getFirstName() == null
                && getEmployeeRequestParam.getLastName() == null
                && getEmployeeRequestParam.getPosition() != null;
        if (isPosition) {
            return employeeRepository.findAllByPositionName(getEmployeeRequestParam.getPosition())
                    .stream()
                    .map(employeeMapper::toDto)
                    .collect(Collectors.toList());
        }

        boolean isFirstNameLastName = getEmployeeRequestParam.getFirstName() != null
                && getEmployeeRequestParam.getLastName() != null
                && getEmployeeRequestParam.getPosition() == null;
        if (isFirstNameLastName) {
            return employeeRepository.findAllByFirstNameAndLastName(getEmployeeRequestParam.getFirstName(),
                    getEmployeeRequestParam.getLastName())
                    .stream()
                    .map(employeeMapper::toDto)
                    .collect(Collectors.toList());
        }

        boolean isFirstNamePosition = getEmployeeRequestParam.getFirstName() != null
                && getEmployeeRequestParam.getLastName() == null
                && getEmployeeRequestParam.getPosition() != null;
        if (isFirstNamePosition) {
            return employeeRepository.findAllByFirstNameAndPositionName(getEmployeeRequestParam.getFirstName(),
                    getEmployeeRequestParam.getPosition())
                    .stream()
                    .map(employeeMapper::toDto)
                    .collect(Collectors.toList());
        }

        boolean isLastNamePosition = getEmployeeRequestParam.getFirstName() == null
                && getEmployeeRequestParam.getLastName() != null
                && getEmployeeRequestParam.getPosition() != null;
        if (isLastNamePosition) {
            return employeeRepository.findAllByLastNameAndPositionName(getEmployeeRequestParam.getLastName(),
                    getEmployeeRequestParam.getPosition())
                    .stream()
                    .map(employeeMapper::toDto)
                    .collect(Collectors.toList());
        }

        boolean isFirstNameAndLastNameAndPositionName = getEmployeeRequestParam.getFirstName() == null
                && getEmployeeRequestParam.getLastName() != null
                && getEmployeeRequestParam.getPosition() != null;
        if (isFirstNameAndLastNameAndPositionName) {
            return employeeRepository.findAllByFirstNameAndLastNameAndPositionName(getEmployeeRequestParam.getFirstName(),
                    getEmployeeRequestParam.getLastName(),
                    getEmployeeRequestParam.getPosition())
                    .stream()
                    .map(employeeMapper::toDto)
                    .collect(Collectors.toList());
        }

        return employeeRepository.findAll()
                .stream()
                .map(employeeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer employeeId) {
        employeeRepository.deleteById(employeeId);
    }

    @Override
    public void update(EmployeeDto employeeDto, Integer employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with id = " + employeeId + " not found"));

        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employeeRepository.save(employee);
    }
}
