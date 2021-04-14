package by.intexsoft.testproject.simplecompany.controller;

import by.intexsoft.testproject.simplecompany.controller.param.DeleteEmployeeRequestParam;
import by.intexsoft.testproject.simplecompany.controller.param.GetEmployeeRequestParam;
import by.intexsoft.testproject.simplecompany.dto.EmployeeDto;
import by.intexsoft.testproject.simplecompany.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public void createEmployee(@RequestBody EmployeeDto employeeDto) {
        employeeService.createEmployee(employeeDto);
    }

    @GetMapping
    public List<EmployeeDto> getEmployees(GetEmployeeRequestParam getEmployeeRequestParam) {
        return employeeService.getEmployees(getEmployeeRequestParam);
    }

    @DeleteMapping("/{employeeId}")
    public void deleteEmployee(@PathVariable Integer employeeId) {
        employeeService.deleteEmployee(employeeId);
    }

    @DeleteMapping
    public void deleteEmployeeByFullName(DeleteEmployeeRequestParam deleteEmployeeRequestParam) {
        employeeService.deleteEmployeeByFullName(deleteEmployeeRequestParam);
    }

    @PutMapping("/{employeeId}")
    public void updateEmployee(@RequestBody EmployeeDto employeeDto, @PathVariable Integer employeeId) {
        employeeService.updateEmployee(employeeDto, employeeId);
    }
}