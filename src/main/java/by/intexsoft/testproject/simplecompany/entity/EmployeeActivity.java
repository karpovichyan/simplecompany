package by.intexsoft.testproject.simplecompany.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class EmployeeActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer hours;

    @ManyToOne
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    private Plan plan;

    @ManyToOne
    private Activity activity;

    public EmployeeActivity(Integer id, Integer hours, Employee employee, Plan plan, Activity activity) {
        this.id = id;
        this.hours = hours;
        this.employee = employee;
        this.plan = plan;
        this.activity = activity;
    }

    public EmployeeActivity(Integer hours, Employee employee, Plan plan, Activity activity) {
        this.hours = hours;
        this.employee = employee;
        this.plan = plan;
        this.activity = activity;
    }

    public EmployeeActivity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeActivity that = (EmployeeActivity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "EmployeeActivity{" +
                "id=" + id +
                ", hours=" + hours +
                ", employee=" + employee +
                ", plan=" + plan +
                ", activity=" + activity +
                '}';
    }
}
