package by.intexsoft.testproject.simplecompany.service;

import by.intexsoft.testproject.simplecompany.dto.ActivityDto;

import java.util.List;

public interface ActivityService {
    ActivityDto create(ActivityDto activityDto);

    List<ActivityDto> getAll();
}
