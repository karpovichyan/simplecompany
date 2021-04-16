package by.intexsoft.testproject.simplecompany.controller;

import by.intexsoft.testproject.simplecompany.dto.EmployeeActivityDto;
import by.intexsoft.testproject.simplecompany.service.EmployeeActivityService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("employee-activities")
public class EmployeeActivityController {
    private final EmployeeActivityService employeeActivityService;

    public EmployeeActivityController(EmployeeActivityService employeeActivityService) {
        this.employeeActivityService = employeeActivityService;
    }

    @PostMapping
    public EmployeeActivityDto create(@Valid @RequestBody EmployeeActivityDto employeeActivityDto) {
        return employeeActivityService.create(employeeActivityDto);
    }

    @GetMapping
    public List<EmployeeActivityDto> getAll() {
        return employeeActivityService.getAll();
    }
}
