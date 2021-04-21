package by.intexsoft.testproject.simplecompany.controller;

import by.intexsoft.testproject.simplecompany.controller.param.PositionEmployeeRequestParam;
import by.intexsoft.testproject.simplecompany.controller.param.SalaryEmployeeRequestParam;
import by.intexsoft.testproject.simplecompany.dto.EmployeeDto;
import by.intexsoft.testproject.simplecompany.service.EmployeeService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public EmployeeDto create(@Valid @RequestBody EmployeeDto employeeDto) {
        return employeeService.create(employeeDto);
    }

    @GetMapping("/position")
    public List<EmployeeDto> getByFullNameAndPositions(PositionEmployeeRequestParam positionEmployeeRequestParam) {
        return employeeService.getByFullNameAndPositions(positionEmployeeRequestParam);
    }

    @GetMapping("/salary")
    public List<EmployeeDto> getByFullNameAndSalaries(SalaryEmployeeRequestParam salaryEmployeeRequestParam) {
        return employeeService.getByFullNameAndSalaries(salaryEmployeeRequestParam);
    }

    @GetMapping("/absence")
    public List<EmployeeDto> getEmployeesByAbsences(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return employeeService.getEmployeesByAbsences(date);
    }

    @DeleteMapping("/{employeeId}")
    public void delete(@PathVariable Integer employeeId) {
        employeeService.delete(employeeId);
    }

    @PutMapping("/{employeeId}")
    public EmployeeDto update(@Valid @RequestBody EmployeeDto employeeDto, @PathVariable Integer employeeId) {
        return employeeService.update(employeeDto, employeeId);
    }
}