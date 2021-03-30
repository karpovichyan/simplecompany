package by.intexsoft.testproject.simplecompany.service;

import by.intexsoft.testproject.simplecompany.dto.EmployeeDto;

import java.util.Set;

public interface EmployeeService {
    void createEmployee(EmployeeDto employeeDto);

    //Set<EmployeeDto> getEmployers(Integer employerId);
}
