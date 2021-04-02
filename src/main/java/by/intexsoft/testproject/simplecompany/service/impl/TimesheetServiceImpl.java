package by.intexsoft.testproject.simplecompany.service.impl;

import by.intexsoft.testproject.simplecompany.dto.TimesheetDto;
import by.intexsoft.testproject.simplecompany.entity.Employee;
import by.intexsoft.testproject.simplecompany.entity.Plan;
import by.intexsoft.testproject.simplecompany.exception.EmployeeNotFoundException;
import by.intexsoft.testproject.simplecompany.repository.EmployeeRepository;
import by.intexsoft.testproject.simplecompany.repository.TimesheetRepository;
import by.intexsoft.testproject.simplecompany.service.TimesheetService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TimesheetServiceImpl implements TimesheetService {
    @Override
    public void createTimesheet(TimesheetDto timesheetDto) {

    }
    /*private final TimesheetRepository timesheetRepository;
    private final EmployeeRepository employeeRepository;

    public TimesheetServiceImpl(TimesheetRepository timesheetRepository, EmployeeRepository employeeRepository) {
        this.timesheetRepository = timesheetRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void createTimesheet(TimesheetDto timesheetDto) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(timesheetDto.getId());
        if (!optionalEmployee.isPresent()) {
            throw new EmployeeNotFoundException("Employee with " + timesheetDto.getId() + " is not found");
        }
        Plan plan = new Plan(
                timesheetDto.getMonth(),
                timesheetDto.getYear(),
                timesheetDto.getTotalHours(),
                optionalEmployee.get()
        );
        timesheetRepository.save(plan);
    }*/
}
