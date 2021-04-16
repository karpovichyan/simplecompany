package by.intexsoft.testproject.simplecompany.service;

import by.intexsoft.testproject.simplecompany.controller.param.EmployeeRequestParam;
import by.intexsoft.testproject.simplecompany.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDto create(EmployeeDto employeeDto);

    void delete(Integer employeeId);

    List<EmployeeDto> get(EmployeeRequestParam getEmployeeRequestParam);

    void update(EmployeeDto employeeDto, Integer employeeId);
}
