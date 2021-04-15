package by.intexsoft.testproject.simplecompany.controller;

import by.intexsoft.testproject.simplecompany.controller.param.EmployeeRequestParam;
import by.intexsoft.testproject.simplecompany.dto.EmployeeDto;
import by.intexsoft.testproject.simplecompany.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public EmployeeDto create(@RequestBody EmployeeDto employeeDto) {
        return employeeService.create(employeeDto);
    }

    @GetMapping
    public List<EmployeeDto> get(EmployeeRequestParam getEmployeeRequestParam) {
        return employeeService.get(getEmployeeRequestParam);
    }

    @DeleteMapping("/{employeeId}")
    public void delete(@PathVariable Integer employeeId) {
        employeeService.delete(employeeId);
    }

    @PutMapping("/{employeeId}")
    public void update(@RequestBody EmployeeDto employeeDto, @PathVariable Integer employeeId) {
        employeeService.update(employeeDto, employeeId);
    }
}