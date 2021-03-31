package by.intexsoft.testproject.simplecompany.service.impl;

import by.intexsoft.testproject.simplecompany.dto.TimesheetDto;
import by.intexsoft.testproject.simplecompany.entity.TimesheetEntity;
import by.intexsoft.testproject.simplecompany.repository.TimesheetRepository;
import by.intexsoft.testproject.simplecompany.service.TimesheetService;
import org.springframework.stereotype.Service;

@Service
public class TimesheetServiceImpl implements TimesheetService {
    private final TimesheetRepository timesheetRepository;

    public TimesheetServiceImpl(TimesheetRepository timesheetRepository) {
        this.timesheetRepository = timesheetRepository;
    }

    @Override
    public void createTimesheet(TimesheetDto timesheetDto) {
        TimesheetEntity timesheetEntity = new TimesheetEntity(
                timesheetDto.getMonth(),
                timesheetDto.getYear(),
                timesheetDto.getTotalHours()
        );
        timesheetRepository.save(timesheetEntity);
    }
}
