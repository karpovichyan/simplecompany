package by.intexsoft.testproject.simplecompany.dto;

import by.intexsoft.testproject.simplecompany.entity.enumeration.ActivityType;

import java.math.BigDecimal;

public class ActivityDto {
    private Integer id;
    private ActivityType activityType;
    private BigDecimal ratio;

    public ActivityDto(Integer id, ActivityType activityType, BigDecimal ratio) {
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

    public BigDecimal getRatio() {
        return ratio;
    }

    public void setRatio(BigDecimal ratio) {
        this.ratio = ratio;
    }
}
