package by.intexsoft.testproject.simplecompany.service;

import by.intexsoft.testproject.simplecompany.dto.EmployeeActivityDto;

import java.util.List;

public interface EmployeeActivityService {
    EmployeeActivityDto create(EmployeeActivityDto employeeActivityDto);

    List<EmployeeActivityDto> getAll();
}
