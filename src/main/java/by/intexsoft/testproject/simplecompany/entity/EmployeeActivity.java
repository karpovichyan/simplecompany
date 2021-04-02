package by.intexsoft.testproject.simplecompany.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "employee_activity")
public class EmployeeActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private int hours;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "plan_id")
    private Plan plan;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private ActivityTypeInfo activityTypeInfo;

    public EmployeeActivity(int hours, Employee employee, Plan plan, ActivityTypeInfo activityTypeInfo) {
        this.hours = hours;
        this.employee = employee;
        this.plan = plan;
        this.activityTypeInfo = activityTypeInfo;
    }

    public EmployeeActivity() {
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public Plan getTimesheet() {
        return plan;
    }

    public void setTimesheet(Plan plan) {
        this.plan = plan;
    }

    public ActivityTypeInfo getActivityTypeInfo() {
        return activityTypeInfo;
    }

    public void setActivityTypeInfo(ActivityTypeInfo activityTypeInfo) {
        this.activityTypeInfo = activityTypeInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeActivity employeeActivity = (EmployeeActivity) o;
        return id == employeeActivity.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
