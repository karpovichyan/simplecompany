package by.intexsoft.testproject.simplecompany.controller;

import by.intexsoft.testproject.simplecompany.dto.TimesheetDto;
import by.intexsoft.testproject.simplecompany.service.TimesheetService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/timesheets")
public class TimesheetController {
    private final TimesheetService timesheetService;

    public TimesheetController(TimesheetService timesheetService) {
        this.timesheetService = timesheetService;
    }

    @PostMapping
    public void createTimesheet(@RequestBody TimesheetDto timesheetDto) {
        timesheetService.createTimesheet(timesheetDto);
    }
}
