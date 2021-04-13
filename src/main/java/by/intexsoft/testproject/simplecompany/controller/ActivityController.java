package by.intexsoft.testproject.simplecompany.controller;

import by.intexsoft.testproject.simplecompany.dto.ActivityDto;
import by.intexsoft.testproject.simplecompany.service.ActivityService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/activities")
public class ActivityController {
    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @PostMapping
    public void createActivity(@RequestBody ActivityDto activityDto) {
        activityService.createActivity(activityDto);
    }

    @GetMapping
    public List<ActivityDto> getAllActivities() {
        return activityService.getAllActivities();
    }
}
