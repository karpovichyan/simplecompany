package by.intexsoft.testproject.simplecompany.controller;

import by.intexsoft.testproject.simplecompany.dto.EmployeeActivityDto;
import by.intexsoft.testproject.simplecompany.service.EmployeeActivityService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public List<EmployeeActivityDto> getAllEmployeeActivities() {
        return employeeActivityService.getAllEmployeeActivities();
    }
}
