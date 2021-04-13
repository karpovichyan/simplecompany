package by.intexsoft.testproject.simplecompany.mapper;

import by.intexsoft.testproject.simplecompany.dto.EmployeeActivityDto;
import by.intexsoft.testproject.simplecompany.entity.Activity;
import by.intexsoft.testproject.simplecompany.entity.Employee;
import by.intexsoft.testproject.simplecompany.entity.EmployeeActivity;
import by.intexsoft.testproject.simplecompany.entity.Plan;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployeeActivityMapper {
    @Mapping(source = "employeeActivityDto.id", target = "id")
    EmployeeActivity toEntity(
            EmployeeActivityDto employeeActivityDto,
            Plan plan,
            Employee employee,
            Activity activity
    );
}
