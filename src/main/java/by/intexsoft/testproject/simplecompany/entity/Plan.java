package by.intexsoft.testproject.simplecompany.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private Integer totalHours;

    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL)
    private Set<EmployeeActivity> employeeActivities;

    public Plan(Integer id, LocalDate date, Integer totalHours, Set<EmployeeActivity> employeeActivities) {
        this.id = id;
        this.date = date;
        this.totalHours = totalHours;
        this.employeeActivities = employeeActivities;
    }

    public Plan(LocalDate date, Integer totalHours, Set<EmployeeActivity> employeeActivities) {
        this.date = date;
        this.totalHours = totalHours;
        this.employeeActivities = employeeActivities;
    }

    public Plan(Integer id, LocalDate date, Integer totalHours) {
        this.id = id;
        this.date = date;
        this.totalHours = totalHours;
    }

    public Plan() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(Integer totalHours) {
        this.totalHours = totalHours;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Set<EmployeeActivity> getEmployeeActivities() {
        return employeeActivities;
    }

    public void setEmployeeActivities(Set<EmployeeActivity> employeeActivities) {
        this.employeeActivities = employeeActivities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plan plan = (Plan) o;
        return id.equals(plan.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Plan{" +
                "id=" + id +
                ", date=" + date +
                ", totalHours=" + totalHours +
                '}';
    }
}
