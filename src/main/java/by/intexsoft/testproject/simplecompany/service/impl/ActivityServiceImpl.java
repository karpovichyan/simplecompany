package by.intexsoft.testproject.simplecompany.service.impl;

import by.intexsoft.testproject.simplecompany.dto.ActivityDto;
import by.intexsoft.testproject.simplecompany.mapper.ActivityMapper;
import by.intexsoft.testproject.simplecompany.repository.ActivityRepository;
import by.intexsoft.testproject.simplecompany.service.ActivityService;
import org.springframework.stereotype.Service;

@Service
public class ActivityServiceImpl implements ActivityService {
    private final ActivityRepository activityRepository;
    private final ActivityMapper activityMapper;

    public ActivityServiceImpl(ActivityRepository activityRepository, ActivityMapper activityMapper) {
        this.activityRepository = activityRepository;
        this.activityMapper = activityMapper;
    }

    @Override
    public void createActivity(ActivityDto activityDto) {
        activityRepository.save(activityMapper.activityDtoToActivity(activityDto));
    }
}
