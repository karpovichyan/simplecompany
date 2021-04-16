package by.intexsoft.testproject.simplecompany.dto;

public class EmployeeActivityDto {
    private Integer id;
    private Integer hours;
    private Integer planId;
    private Integer employeeId;
    private Integer activityId;

    public EmployeeActivityDto(Integer id, Integer hours, Integer planId, Integer employeeId, Integer activityId) {
        this.id = id;
        this.hours = hours;
        this.planId = planId;
        this.employeeId = employeeId;
        this.activityId = activityId;
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

    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }
}
