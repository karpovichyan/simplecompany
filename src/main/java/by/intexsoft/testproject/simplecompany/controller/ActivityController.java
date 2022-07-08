package by.intexsoft.testproject.simplecompany.controller;

import by.intexsoft.testproject.simplecompany.dto.ActivityDto;
import by.intexsoft.testproject.simplecompany.service.ActivityService;
import by.intexsoft.testproject.simplecompany.service.impl.PayslipServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("activities")
public class ActivityController {

    private final Logger log = LoggerFactory.getLogger(ActivityController.class);
    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @PostMapping
    public ActivityDto create(@Valid @RequestBody ActivityDto activityDto) {
        return activityService.create(activityDto);
    }

    @GetMapping
    public List<ActivityDto> getAll() {
        return activityService.getAll();
    }

    @GetMapping
    public List<ActivityDto> newfeature3() {
        log.info("");
        return activityService.getAll();
    }

}
