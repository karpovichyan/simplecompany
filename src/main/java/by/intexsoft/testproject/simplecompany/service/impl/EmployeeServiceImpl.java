package by.intexsoft.testproject.simplecompany.service.impl;

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
}
