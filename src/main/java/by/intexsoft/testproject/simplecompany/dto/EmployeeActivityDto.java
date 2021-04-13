package by.intexsoft.testproject.simplecompany.dto;

public class EmployeeActivityDto {
    private int id;
    private int hours;
    private int planId;
    private int employeeId;
    private int activityId;

    public EmployeeActivityDto(int id, int hours, int planId, int employeeId, int activityId) {
        this.id = id;
        this.hours = hours;
        this.planId = planId;
        this.employeeId = employeeId;
        this.activityId = activityId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }
}
