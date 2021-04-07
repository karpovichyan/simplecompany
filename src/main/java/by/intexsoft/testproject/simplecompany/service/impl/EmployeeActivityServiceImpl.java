package by.intexsoft.testproject.simplecompany.service.impl;

import by.intexsoft.testproject.simplecompany.dto.EmployeeActivityDto;
import by.intexsoft.testproject.simplecompany.entity.Activity;
import by.intexsoft.testproject.simplecompany.entity.Employee;
import by.intexsoft.testproject.simplecompany.entity.EmployeeActivity;
import by.intexsoft.testproject.simplecompany.entity.Plan;
import by.intexsoft.testproject.simplecompany.exception.ActivityNotFoundException;
import by.intexsoft.testproject.simplecompany.exception.EmployeeNotFoundException;
import by.intexsoft.testproject.simplecompany.exception.PlanNotFoundException;
import by.intexsoft.testproject.simplecompany.mapper.EmployeeActivityMapper;
import by.intexsoft.testproject.simplecompany.repository.ActivityRepository;
import by.intexsoft.testproject.simplecompany.repository.EmployeeActivityRepository;
import by.intexsoft.testproject.simplecompany.repository.EmployeeRepository;
import by.intexsoft.testproject.simplecompany.repository.PlanRepository;
import by.intexsoft.testproject.simplecompany.service.EmployeeActivityService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeActivityServiceImpl implements EmployeeActivityService {
    private final EmployeeActivityRepository employeeActivityRepository;
    private final PlanRepository planRepository;
    private final EmployeeRepository employeeRepository;
    private final ActivityRepository activityRepository;
    private final EmployeeActivityMapper employeeActivityMapper;

    public EmployeeActivityServiceImpl(
            EmployeeActivityRepository employeeActivityRepository,
            PlanRepository planRepository,
            EmployeeRepository employeeRepository,
            ActivityRepository activityRepository,
            EmployeeActivityMapper employeeActivityMapper
    ) {
        this.employeeActivityRepository = employeeActivityRepository;
        this.planRepository = planRepository;
        this.employeeRepository = employeeRepository;
        this.activityRepository = activityRepository;
        this.employeeActivityMapper = employeeActivityMapper;
    }

    @Override
    public void createEmployeeActivity(EmployeeActivityDto employeeActivityDto) {
        Plan plan = planRepository.findById(employeeActivityDto.getPlanId()).
                orElseThrow(() -> new PlanNotFoundException(
                        "Plan with id = " + employeeActivityDto.getPlanId() + " not found!")
                );

        Employee employee = employeeRepository.findById(employeeActivityDto.getEmployeeId()).
                orElseThrow(() -> new EmployeeNotFoundException(
                        "Employee with id = " + employeeActivityDto.getEmployeeId() + " not found!"));

        Activity activity = null;
        if (employeeActivityDto.getActivityId() != null) {
            activity = activityRepository.findById(employeeActivityDto.getActivityId()).
                    orElseThrow(() -> new ActivityNotFoundException(
                            "Activity with id = " + employeeActivityDto.getActivityId() + " not found!")
                    );
        }

        EmployeeActivity employeeActivity = employeeActivityMapper.toEntity(employeeActivityDto, plan, employee, activity);
        employeeActivityRepository.save(employeeActivity);
    }
}
