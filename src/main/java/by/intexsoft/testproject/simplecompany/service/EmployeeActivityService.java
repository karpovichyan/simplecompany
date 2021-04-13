package by.intexsoft.testproject.simplecompany.service;

import by.intexsoft.testproject.simplecompany.dto.EmployeeActivityDto;

import java.util.List;

public interface EmployeeActivityService {
    void createEmployeeActivity(EmployeeActivityDto employeeActivityDto);

    List<EmployeeActivityDto> getAllEmployeeActivities();
}
