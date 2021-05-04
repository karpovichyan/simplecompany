package by.intexsoft.testproject.simplecompany.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

public class EmployeeDto {
    private Integer id;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotNull
    @Valid
    private PositionDto position;
    @NotNull
    @Valid
    private ContractDto contract;
    @NotNull
    @Valid
    @JsonManagedReference
    private Set<EmployeeActivityDto> employeeActivities;

    public EmployeeDto(Integer id, String firstName, String lastName, PositionDto position, ContractDto contract) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.contract = contract;
    }

    public EmployeeDto() {
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PositionDto getPosition() {
        return position;
    }

    public void setPosition(PositionDto position) {
        this.position = position;
    }

    public ContractDto getContract() {
        return contract;
    }

    public void setContract(ContractDto contract) {
        this.contract = contract;
    }

    public Set<EmployeeActivityDto> getEmployeeActivities() {
        return employeeActivities;
    }

    public void setEmployeeActivities(Set<EmployeeActivityDto> employeeActivities) {
        this.employeeActivities = employeeActivities;
    }
}
