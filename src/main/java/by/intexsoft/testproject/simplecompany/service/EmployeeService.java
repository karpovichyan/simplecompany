package by.intexsoft.testproject.simplecompany.service;

import by.intexsoft.testproject.simplecompany.controller.param.PositionEmployeeRequestParam;
import by.intexsoft.testproject.simplecompany.controller.param.SalaryEmployeeRequestParam;
import by.intexsoft.testproject.simplecompany.dto.EmployeeDto;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeService {
    EmployeeDto create(EmployeeDto employeeDto);

    void delete(Integer employeeId);

    EmployeeDto update(EmployeeDto employeeDto, Integer employeeId);

    List<EmployeeDto> getByFullNameAndPositions(PositionEmployeeRequestParam getPositionEmployeeRequestParam);

    List<EmployeeDto> getByFullNameAndSalaries(SalaryEmployeeRequestParam salaryEmployeeRequestParam);

    List<EmployeeDto> getEmployeesByAbsences(LocalDate date);
}
