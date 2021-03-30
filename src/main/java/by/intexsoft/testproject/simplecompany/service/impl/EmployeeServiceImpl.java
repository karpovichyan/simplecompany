package by.intexsoft.testproject.simplecompany.service.impl;

import by.intexsoft.testproject.simplecompany.dto.EmployeeDto;
import by.intexsoft.testproject.simplecompany.entity.EmployeeEntity;
import by.intexsoft.testproject.simplecompany.repository.EmployeeRepository;
import by.intexsoft.testproject.simplecompany.service.EmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void createEmployee(EmployeeDto employeeDto) {
        EmployeeEntity employeeEntity = new EmployeeEntity(employeeDto.getFirstName(), employeeDto.getLastName());
        employeeRepository.save(employeeEntity);
    }

   /*
    @Override
    public Set<EmployeeDto> getEmployers(Integer employerId) {
        return employeeRepository.findByEmployeeId(employerId).stream()
                .map(employeeEntity -> new EmployeeDto(employeeEntity.))
                .collect(Collectors.toSet());
    }

    */
}
