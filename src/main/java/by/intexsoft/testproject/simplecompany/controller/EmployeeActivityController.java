package by.intexsoft.testproject.simplecompany.controller;

import by.intexsoft.testproject.simplecompany.dto.EmployeeActivityDto;
import by.intexsoft.testproject.simplecompany.service.EmployeeActivityService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employeeactivities")
public class EmployeeActivityController {
    private final EmployeeActivityService employeeActivityService;

    public EmployeeActivityController(EmployeeActivityService employeeActivityService) {
        this.employeeActivityService = employeeActivityService;
    }

    @PostMapping
    public void createEmployeeActivity(@RequestBody EmployeeActivityDto employeeActivityDto) {
        employeeActivityService.createEmployeeActivity(employeeActivityDto);
    }
}