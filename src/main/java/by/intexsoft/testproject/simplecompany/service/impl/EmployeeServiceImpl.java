package by.intexsoft.testproject.simplecompany.service.impl;

import by.intexsoft.testproject.simplecompany.controller.param.DeleteEmployeeRequestParam;
import by.intexsoft.testproject.simplecompany.controller.param.GetEmployeeRequestParam;
import by.intexsoft.testproject.simplecompany.dto.EmployeeDto;
import by.intexsoft.testproject.simplecompany.entity.Contract;
import by.intexsoft.testproject.simplecompany.entity.Employee;
import by.intexsoft.testproject.simplecompany.entity.Position;
import by.intexsoft.testproject.simplecompany.exception.ContractNotFoundException;
import by.intexsoft.testproject.simplecompany.exception.PositionNotFoundException;
import by.intexsoft.testproject.simplecompany.mapper.EmployeeMapper;
import by.intexsoft.testproject.simplecompany.repository.ContractRepository;
import by.intexsoft.testproject.simplecompany.repository.EmployeeRepository;
import by.intexsoft.testproject.simplecompany.repository.PositionRepository;
import by.intexsoft.testproject.simplecompany.service.EmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public void createEmployee(EmployeeDto employeeDto) {
        Position position = positionRepository.findById(employeeDto.getPositionId()).
                orElseThrow(() -> new PositionNotFoundException(
                        "Position with id: " + employeeDto.getPositionId() + " not found!"
                ));

        Contract contract = contractRepository.findById(employeeDto.getContractId()).
                orElseThrow(() -> new ContractNotFoundException(
                        "Contract with id: " + employeeDto.getContractId() + " not found!"
                ));

        Employee employee = employeeMapper.employeeDtoToEmpolyee(employeeDto, position, contract);
        employeeRepository.save(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(employeeMapper::employeeToEmployeeDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDto> getEmployees(GetEmployeeRequestParam getEmployeeRequestParam) {
        boolean isFirstName = getEmployeeRequestParam.getFirstName() != null
                && getEmployeeRequestParam.getLastName() == null
                && getEmployeeRequestParam.getPosition() == null;
        if (isFirstName) {
            return employeeRepository.findAllByFirstName(getEmployeeRequestParam.getFirstName())
                    .stream()
                    .map(employeeMapper::employeeToEmployeeDto)
                    .collect(Collectors.toList());
        }

        boolean isLastName = getEmployeeRequestParam.getFirstName() == null
                && getEmployeeRequestParam.getLastName() != null
                && getEmployeeRequestParam.getPosition() == null;
        if (isLastName) {
            return employeeRepository.findAllByLastName(getEmployeeRequestParam.getLastName())
                    .stream()
                    .map(employeeMapper::employeeToEmployeeDto)
                    .collect(Collectors.toList());
        }

        boolean isPosition = getEmployeeRequestParam.getFirstName() == null
                && getEmployeeRequestParam.getLastName() == null
                && getEmployeeRequestParam.getPosition() != null;
        if (isPosition) {
            return employeeRepository.findAllByPositionName(getEmployeeRequestParam.getPosition())
                    .stream()
                    .map(employeeMapper::employeeToEmployeeDto)
                    .collect(Collectors.toList());
        }

        boolean isFirstNameLastName = getEmployeeRequestParam.getFirstName() != null
                && getEmployeeRequestParam.getLastName() != null
                && getEmployeeRequestParam.getPosition() == null;
        if (isFirstNameLastName) {
            return employeeRepository.findAllByFirstNameAndLastName(getEmployeeRequestParam.getFirstName(),
                    getEmployeeRequestParam.getLastName())
                    .stream()
                    .map(employeeMapper::employeeToEmployeeDto)
                    .collect(Collectors.toList());
        }

        boolean isFirstNamePosition = getEmployeeRequestParam.getFirstName() != null
                && getEmployeeRequestParam.getLastName() == null
                && getEmployeeRequestParam.getPosition() != null;
        if (isFirstNamePosition) {
            return employeeRepository.findAllByFirstNameAndPositionName(getEmployeeRequestParam.getFirstName(),
                    getEmployeeRequestParam.getPosition())
                    .stream()
                    .map(employeeMapper::employeeToEmployeeDto)
                    .collect(Collectors.toList());
        }

        boolean isLastNamePosition = getEmployeeRequestParam.getFirstName() == null
                && getEmployeeRequestParam.getLastName() != null
                && getEmployeeRequestParam.getPosition() != null;
        if (isLastNamePosition) {
            return employeeRepository.findAllByLastNameAndPositionName(getEmployeeRequestParam.getLastName(),
                    getEmployeeRequestParam.getPosition())
                    .stream()
                    .map(employeeMapper::employeeToEmployeeDto)
                    .collect(Collectors.toList());
        }
        return employeeRepository.findAllByFirstNameAndLastNameAndPositionName(getEmployeeRequestParam.getFirstName(),
                getEmployeeRequestParam.getLastName(),
                getEmployeeRequestParam.getPosition())
                .stream()
                .map(employeeMapper::employeeToEmployeeDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteEmployee(Integer employeeId) {
        employeeRepository.deleteById(employeeId);
    }

    @Override
    @Transactional
    public void deleteEmployeeByFullName(DeleteEmployeeRequestParam deleteEmployeeRequestParam) {
        employeeRepository.deleteByFirstNameAndLastName(deleteEmployeeRequestParam.getFirstName(),
                deleteEmployeeRequestParam.getLastName());
    }
}
