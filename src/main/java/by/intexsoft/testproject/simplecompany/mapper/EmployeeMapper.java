package by.intexsoft.testproject.simplecompany.mapper;

import by.intexsoft.testproject.simplecompany.dto.EmployeeDto;
import by.intexsoft.testproject.simplecompany.entity.Contract;
import by.intexsoft.testproject.simplecompany.entity.Employee;
import by.intexsoft.testproject.simplecompany.entity.Position;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    @Mapping(source = "employeeDto.id", target = "id")
    Employee employeeDtoToEmpolyee(EmployeeDto employeeDto, Position position, Contract contract);


    @Mapping(source = "employee.id", target = "id")
    @Mapping(source = "employee.position.id", target = "positionId")
    @Mapping(source = "employee.contract.id", target = "contractId")
    EmployeeDto employeeToEmployeeDto(Employee employee);
}
