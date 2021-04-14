package by.intexsoft.testproject.simplecompany.service;

import by.intexsoft.testproject.simplecompany.controller.param.DeleteEmployeeRequestParam;
import by.intexsoft.testproject.simplecompany.controller.param.GetEmployeeRequestParam;
import by.intexsoft.testproject.simplecompany.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    void createEmployee(EmployeeDto employeeDto);

    void deleteEmployee(Integer employeeId);

    void deleteEmployeeByFullName(DeleteEmployeeRequestParam deleteEmployeeRequestParam);

    List<EmployeeDto> getEmployees(GetEmployeeRequestParam getEmployeeRequestParam);

    void updateEmployee(EmployeeDto employeeDto, Integer employeeId);
}
