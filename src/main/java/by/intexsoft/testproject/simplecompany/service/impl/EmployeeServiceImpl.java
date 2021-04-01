package by.intexsoft.testproject.simplecompany.service.impl;

import by.intexsoft.testproject.simplecompany.dto.EmployeeDto;
import by.intexsoft.testproject.simplecompany.entity.Employee;
import by.intexsoft.testproject.simplecompany.entity.JobInfo;
import by.intexsoft.testproject.simplecompany.exception.PositionNotFoundException;
import by.intexsoft.testproject.simplecompany.repository.EmployeeRepository;
import by.intexsoft.testproject.simplecompany.repository.JobInfoRepository;
import by.intexsoft.testproject.simplecompany.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final JobInfoRepository jobInfoRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, JobInfoRepository jobInfoRepository) {
        this.employeeRepository = employeeRepository;
        this.jobInfoRepository = jobInfoRepository;
    }

    @Override
    public void createEmployee(EmployeeDto employeeDto) {
        Optional<JobInfo> optionalJobInfo = jobInfoRepository.findById(employeeDto.getPosition());
        if (!optionalJobInfo.isPresent()) {
            throw new PositionNotFoundException("Position " + employeeDto.getPosition() + " not found!");
        }
        Employee employee = new Employee(
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                optionalJobInfo.get()
        );
        employeeRepository.save(employee);
    }
}
