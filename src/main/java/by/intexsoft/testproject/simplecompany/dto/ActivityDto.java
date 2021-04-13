package by.intexsoft.testproject.simplecompany.dto;

import by.intexsoft.testproject.simplecompany.entity.ActivityType;

import java.math.BigDecimal;

public class ActivityDto {
    private int id;
    private ActivityType activityType;
    private BigDecimal ratio;

    public ActivityDto(int id, ActivityType activityType, BigDecimal ratio) {
        this.id = id;
        this.activityType = activityType;
        this.ratio = ratio;
    }

    public ActivityDto() {
    }

    public ActivityType getActivityType() {
        return activityType;
    }

    public void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
    }

    public BigDecimal getRatio() {
        return ratio;
    }

    public void setRatio(BigDecimal ratio) {
        this.ratio = ratio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
