package by.intexsoft.testproject.simplecompany.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;

    @OneToMany(mappedBy = "employee")
    private Set<EmployeeActivity> employeeActivities;

    public Employee(String firstName, String lastName, Position position) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
    }

    public Employee() {
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

    public Position getJobInfo() {
        return position;
    }

    public void setJobInfo(Position position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
