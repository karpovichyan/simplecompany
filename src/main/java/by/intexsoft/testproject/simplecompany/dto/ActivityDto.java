package by.intexsoft.testproject.simplecompany.dto;

import by.intexsoft.testproject.simplecompany.entity.enumeration.ActivityType;

import javax.validation.constraints.NotNull;

public class ActivityDto {
    private Integer id;
    @NotNull
    private ActivityType activityType;
    @NotNull
    private Double ratio;

    public ActivityDto(Integer id, ActivityType activityType, Double ratio) {
        this.id = id;
        this.activityType = activityType;
        this.ratio = ratio;
    }

    public ActivityDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ActivityType getActivityType() {
        return activityType;
    }

    public void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
    }

    public Double getRatio() {
        return ratio;
    }

    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }
}
