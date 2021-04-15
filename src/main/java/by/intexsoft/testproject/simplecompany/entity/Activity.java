package by.intexsoft.testproject.simplecompany.entity;

import by.intexsoft.testproject.simplecompany.entity.enumeration.ActivityType;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

@Entity
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @Enumerated(EnumType.STRING)
    private ActivityType activityType;

    @Column(nullable = false, precision = 2, scale = 1)
    @Digits(integer = 2, fraction = 1)
    private BigDecimal ratio;

    @OneToMany(mappedBy = "activity")
    private Set<EmployeeActivity> employeeActivities;

    public Activity(ActivityType activityType, BigDecimal ratio, Set<EmployeeActivity> employeeActivities) {
        this.activityType = activityType;
        this.ratio = ratio;
        this.employeeActivities = employeeActivities;
    }

    public Activity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getRatio() {
        return ratio;
    }

    public void setRatio(BigDecimal ratio) {
        this.ratio = ratio;
    }

    public ActivityType getActivityType() {
        return activityType;
    }

    public void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
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
        Activity activity = (Activity) o;
        return id.equals(activity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
