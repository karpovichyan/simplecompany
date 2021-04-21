package by.intexsoft.testproject.simplecompany.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @ManyToOne(fetch = FetchType.LAZY)
    private Position position;

    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private Contract contract;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.REMOVE)
    private Set<EmployeeActivity> employeeActivities;

    public Employee(String firstName, String lastName, Position position, Contract contract) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.contract = contract;
    }

    public Employee() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Set<EmployeeActivity> getEmployeeActivities() {
        return employeeActivities;
    }

    public void setEmployeeActivities(Set<EmployeeActivity> employeeActivities) {
        this.employeeActivities = employeeActivities;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id.equals(employee.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
