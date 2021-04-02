package by.intexsoft.testproject.simplecompany.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "plan")
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private int month;

    @Column
    private int year;

    @Column
    private int totalHours;

    @OneToMany(mappedBy = "plan")
    private Set<EmployeeActivity> employeeActivities;

    public Plan(int month, int year, int totalHours) {
        this.month = month;
        this.year = year;
        this.totalHours = totalHours;
    }

    public Plan() {
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(int totalHours) {
        this.totalHours = totalHours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plan plan = (Plan) o;
        return id == plan.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
