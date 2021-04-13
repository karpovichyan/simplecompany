package by.intexsoft.testproject.simplecompany.dto;

public class EmployeeDto {
    private int id;
    private String firstName;
    private String lastName;
    private int positionId;
    private int contractId;

    public EmployeeDto(int id, String firstName, String lastName, int contractId, int positionId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.contractId = contractId;
        this.positionId = positionId;
    }

    public EmployeeDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getContractId() {
        return contractId;
    }

    public void setContractId(int contractId) {
        this.contractId = contractId;
    }

    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }
}
