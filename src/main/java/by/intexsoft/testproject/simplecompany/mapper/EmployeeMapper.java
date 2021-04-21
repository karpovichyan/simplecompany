package by.intexsoft.testproject.simplecompany.mapper;

import by.intexsoft.testproject.simplecompany.dto.EmployeeDto;
import by.intexsoft.testproject.simplecompany.entity.Contract;
import by.intexsoft.testproject.simplecompany.entity.Employee;
import by.intexsoft.testproject.simplecompany.entity.Position;
import by.intexsoft.testproject.simplecompany.mapper.context.CycleAvoidingMappingContext;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    @Mapping(target = "id", ignore = true)
    Employee toEntity(EmployeeDto employeeDto, Position position, Contract contract, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @Mapping(source = "employee.id", target = "id")
    EmployeeDto toDto(Employee employee, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @Mapping(target = "employee.id", ignore = true)
    void updateFromDto(EmployeeDto employeeDto, @MappingTarget Employee employee, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);
}
