package by.intexsoft.testproject.simplecompany.dto;

public class EmployeeDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private Integer positionId;
    private Integer contractId;

    public EmployeeDto(Integer id, String firstName, String lastName, Integer positionId, Integer contractId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.positionId = positionId;
        this.contractId = contractId;
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

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public Integer getContractId() {
        return contractId;
    }

    public void setContractId(Integer contractId) {
        this.contractId = contractId;
    }
}
