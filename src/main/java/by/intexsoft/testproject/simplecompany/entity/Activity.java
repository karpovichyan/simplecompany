package by.intexsoft.testproject.simplecompany.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private int hours;

    @ManyToOne
    @JoinColumn(name = "timesheet_id")
    private Timesheet timesheet;

    @OneToOne
    @JoinColumn(name = "type_info")
    private ActivityTypeInfo activityTypeInfo;

    public Activity(int hours, Timesheet timesheet, ActivityTypeInfo activityTypeInfo) {
        this.hours = hours;
        this.timesheet = timesheet;
        this.activityTypeInfo = activityTypeInfo;
    }

    public Activity() {
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public Timesheet getTimesheet() {
        return timesheet;
    }

    public void setTimesheet(Timesheet timesheet) {
        this.timesheet = timesheet;
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
        Activity activity = (Activity) o;
        return id == activity.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
