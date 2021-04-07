package by.intexsoft.testproject.simplecompany.mapper;

import by.intexsoft.testproject.simplecompany.dto.EmployeeDto;
import by.intexsoft.testproject.simplecompany.entity.Contract;
import by.intexsoft.testproject.simplecompany.entity.Employee;
import by.intexsoft.testproject.simplecompany.entity.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;

class EmployeeMapperTest {

    @Test
    @DisplayName("Should successfully map EmployeeDto to Employee")
    void successfullyMapping() {
        Position position = new Position();
        position.setId(1);

        Contract contract = new Contract();
        position.setId(1);

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(1);
        employeeDto.setFirstName("yan");
        employeeDto.setLastName("karpovich");

        EmployeeMapper employeeMapper = Mappers.getMapper(EmployeeMapper.class);
        Employee employee = employeeMapper.employeeDtoToEmpolyee(employeeDto, position, contract);

        assertThat(employee.getId()).isEqualTo(1);
        assertThat(employee.getFirstName()).isEqualTo("yan");
        assertThat(employee.getLastName()).isEqualTo("karpovich");
        assertThat(employee.getPosition()).isEqualTo(position);
        assertThat(employee.getContract()).isEqualTo(contract);
    }
}
