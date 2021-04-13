package by.intexsoft.testproject.simplecompany.service;

import by.intexsoft.testproject.simplecompany.dto.ActivityDto;

import java.util.List;

public interface ActivityService {
    void createActivity(ActivityDto activityDto);

    List<ActivityDto> getAllActivities();
}
