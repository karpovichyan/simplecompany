package by.intexsoft.testproject.simplecompany.service;

import by.intexsoft.testproject.simplecompany.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    void createEmployee(EmployeeDto employeeDto);

    List<EmployeeDto> getAllEmployees();
}
