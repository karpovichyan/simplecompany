package by.intexsoft.testproject.simplecompany.service.impl;

import by.intexsoft.testproject.simplecompany.dto.EmployeeDto;
import by.intexsoft.testproject.simplecompany.entity.*;
import by.intexsoft.testproject.simplecompany.mapper.EmployeeMapper;
import by.intexsoft.testproject.simplecompany.repository.PlanRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static by.intexsoft.testproject.simplecompany.entity.enumeration.ActivityType.PRESENT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private PlanRepository planRepository;
    @Mock
    private EmployeeMapper employeeMapper;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    @DisplayName("Should return empty list when employee has no absence")
    void getEmployeesByAbsence_shouldReturnEmptyList() {
        LocalDate date = LocalDate.now();

        Position position = new Position("Junior Developer", 300);
        position.setId(1);

        Contract contract = new Contract(LocalDate.parse("2021-01-01"));
        contract.setId(1);

        Employee employee = new Employee("firstName", "lastName", position, contract);
        employee.setId(1);

        Activity activity = new Activity(1, PRESENT, BigDecimal.valueOf(1));

        HashSet<EmployeeActivity> employeeActivities = new HashSet<>();

        Plan plan = new Plan(date, 160, employeeActivities);
        plan.setId(1);

        EmployeeActivity employeeActivityElem = new EmployeeActivity(160, employee, plan, activity);
        employeeActivityElem.setId(1);

        employeeActivities.add(employeeActivityElem);
        when(planRepository.findByDate(date)).thenReturn(Optional.of(plan));

        List<EmployeeDto> employeesByAbsences = employeeService.getEmployeesByAbsences(date);

        assertThat(employeesByAbsences).hasSize(0);

        verify(planRepository).findByDate(date);

        verifyNoMoreInteractions(planRepository);
        verifyNoInteractions(employeeMapper);
    }
}
