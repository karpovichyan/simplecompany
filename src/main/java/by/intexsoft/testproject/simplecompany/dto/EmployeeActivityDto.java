package by.intexsoft.testproject.simplecompany.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class EmployeeActivityDto {
    private Integer id;
    @NotNull
    private Integer hours;
    @NotNull
    @Valid
    private PlanDto plan;
    @NotNull
    @Valid
    @JsonBackReference
    private EmployeeDto employee;
    @NotNull
    @Valid
    private ActivityDto activity;

    public EmployeeActivityDto(Integer id, Integer hours, PlanDto plan, EmployeeDto employee, ActivityDto activity) {
        this.id = id;
        this.hours = hours;
        this.plan = plan;
        this.employee = employee;
        this.activity = activity;
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

    public PlanDto getPlan() {
        return plan;
    }

    public void setPlan(PlanDto plan) {
        this.plan = plan;
    }

    public EmployeeDto getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeDto employee) {
        this.employee = employee;
    }

    public ActivityDto getActivity() {
        return activity;
    }

    public void setActivity(ActivityDto activity) {
        this.activity = activity;
    }
}
