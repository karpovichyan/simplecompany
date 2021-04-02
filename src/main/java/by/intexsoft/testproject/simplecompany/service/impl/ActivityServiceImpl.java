package by.intexsoft.testproject.simplecompany.service.impl;

import by.intexsoft.testproject.simplecompany.dto.ActivityDto;
import by.intexsoft.testproject.simplecompany.entity.EmployeeActivity;
import by.intexsoft.testproject.simplecompany.entity.ActivityTypeInfo;
import by.intexsoft.testproject.simplecompany.entity.Plan;
import by.intexsoft.testproject.simplecompany.exception.ActivityTypeNotFoundException;
import by.intexsoft.testproject.simplecompany.exception.TimesheetNotFoundException;
import by.intexsoft.testproject.simplecompany.repository.ActivityRepository;
import by.intexsoft.testproject.simplecompany.repository.ActivityTypeInfoRepository;
import by.intexsoft.testproject.simplecompany.repository.TimesheetRepository;
import by.intexsoft.testproject.simplecompany.service.ActivityService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ActivityServiceImpl implements ActivityService {
    @Override
    public void createActivity(ActivityDto activityDto) {

    }
    /*private final TimesheetRepository timesheetRepository;
    private final ActivityRepository activityRepository;
    private final ActivityTypeInfoRepository activityTypeInfoRepository;

    public ActivityServiceImpl(TimesheetRepository timesheetRepository,
                               ActivityRepository activityRepository,
                               ActivityTypeInfoRepository activityTypeInfoRepository) {
        this.timesheetRepository = timesheetRepository;
        this.activityRepository = activityRepository;
        this.activityTypeInfoRepository = activityTypeInfoRepository;
    }

    @Override
    public void createActivity(ActivityDto activityDto) {
        Optional<Plan> optionalTimesheet = timesheetRepository.findById(activityDto.getId());
        if (!optionalTimesheet.isPresent()) {
            throw new TimesheetNotFoundException("Timesheet with " + activityDto.getId() + " is not found");
        }
        Optional<ActivityTypeInfo> optionalActivityTypeInfo = activityTypeInfoRepository.findById(activityDto.getType());
        if (!optionalActivityTypeInfo.isPresent()) {
            throw new ActivityTypeNotFoundException("Activity type " + activityDto.getType() + " not found");
        }
        EmployeeActivity employeeActivity = new EmployeeActivity(activityDto.getHours(), optionalTimesheet.get(), optionalActivityTypeInfo.get());
        activityRepository.save(employeeActivity);
    }*/
}
