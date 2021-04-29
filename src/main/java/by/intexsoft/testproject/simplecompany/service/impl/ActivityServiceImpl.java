package by.intexsoft.testproject.simplecompany.service.impl;

import by.intexsoft.testproject.simplecompany.dto.ActivityDto;
import by.intexsoft.testproject.simplecompany.entity.Activity;
import by.intexsoft.testproject.simplecompany.mapper.ActivityMapper;
import by.intexsoft.testproject.simplecompany.repository.ActivityRepository;
import by.intexsoft.testproject.simplecompany.service.ActivityService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActivityServiceImpl implements ActivityService {
    private final ActivityRepository activityRepository;
    private final ActivityMapper activityMapper;

    public ActivityServiceImpl(ActivityRepository activityRepository, ActivityMapper activityMapper) {
        this.activityRepository = activityRepository;
        this.activityMapper = activityMapper;
    }

    @Override
    public ActivityDto create(ActivityDto activityDto) {
        if (activityDto == null) {
            throw new IllegalArgumentException("activityDto should not be null");
        }
        Activity activity = activityMapper.toEntity(activityDto);
        return activityMapper.toDto(activityRepository.save(activity));
    }

    @Override
    public List<ActivityDto> getAll() {
        return activityRepository.findAll()
                .stream()
                .map(activityMapper::toDto)
                .collect(Collectors.toList());
    }
}
