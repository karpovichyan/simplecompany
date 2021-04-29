package by.intexsoft.testproject.simplecompany.service.impl;

import by.intexsoft.testproject.simplecompany.entity.*;
import by.intexsoft.testproject.simplecompany.entity.enumeration.ActivityType;
import by.intexsoft.testproject.simplecompany.repository.PlanRepository;
import by.intexsoft.testproject.simplecompany.service.payslip.PayslipWriter;
import by.intexsoft.testproject.simplecompany.service.payslip.execute.PayslipGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PayslipServiceImplTest {

    @Mock
    private PayslipGenerator payslipGenerator;
    @Mock
    private PlanRepository planRepository;
    @Mock
    private PayslipWriter payslipWriter;

    @InjectMocks
    private PayslipServiceImpl payslipService;


    @Test
    @DisplayName("Should create missing payslips")
    void createSuccessfullyCreatePayslips() throws IOException, InterruptedException {
        LocalDate date = LocalDate.now();

        Activity activity = new Activity(1, ActivityType.PRESENT, BigDecimal.valueOf(1.0));
        Position position = new Position(1, "Junior Developer", 300);
        Contract contract = new Contract(1, LocalDate.parse("2021-01-01"));
        Employee employee1 = new Employee(1, "firstName", "lastName", position, contract);
        Employee employee2 = new Employee(2, "firstName1", "lastName1", position, contract);

        Set<EmployeeActivity> employeeActivities = new HashSet<>();
        Plan plan = new Plan(1, LocalDate.parse("2021-06-30"), 100, employeeActivities);

        EmployeeActivity employeeActivity1 = new EmployeeActivity(1, 20, employee1, plan, activity);
        EmployeeActivity employeeActivity2 = new EmployeeActivity(2, 40, employee2, plan, activity);
        employeeActivities.add(employeeActivity1);
        employeeActivities.add(employeeActivity2);

        doNothing().when(payslipWriter).createDirectory(date);
        when(payslipWriter.getExistPayslipIds(date)).thenReturn(Collections.emptyList());
        when(planRepository.findByDate(date)).thenReturn(Optional.of(plan));
        doNothing().when(payslipGenerator).generateResult(Collections.singletonList(1));
        doNothing().when(payslipGenerator).generateResult(Collections.singletonList(2));

        payslipService.create(date);

        Thread.sleep(1000);
        verify(payslipWriter).createDirectory(date);
        verify(payslipWriter).getExistPayslipIds(date);
        verify(planRepository).findByDate(date);
        verify(payslipGenerator).generateResult(Collections.singletonList(1));
        verify(payslipGenerator).generateResult(Collections.singletonList(2));

        verifyNoMoreInteractions(payslipWriter, planRepository, payslipGenerator);
    }

    @Test
    @DisplayName("Shouldn't create payslips when payslips already exists")
    void createShouldNotCreatePayslips() throws IOException {
        LocalDate date = LocalDate.now();

        Activity activity = new Activity(1, ActivityType.PRESENT, BigDecimal.valueOf(1.0));
        Position position = new Position(1, "Junior Developer", 300);
        Contract contract = new Contract(1, LocalDate.parse("2021-01-01"));
        Employee employee = new Employee(1, "firstName", "lastName", position, contract);

        Set<EmployeeActivity> employeeActivities = new HashSet<>();
        Plan plan = new Plan(1, LocalDate.parse("2021-06-30"), 100, employeeActivities);

        EmployeeActivity employeeActivity = new EmployeeActivity(1, 20, employee, plan, activity);
        employeeActivities.add(employeeActivity);

        doNothing().when(payslipWriter).createDirectory(date);
        when(payslipWriter.getExistPayslipIds(date)).thenReturn(Collections.singletonList("1"));
        when(planRepository.findByDate(date)).thenReturn(Optional.of(plan));

        payslipService.create(date);

        verify(payslipWriter).createDirectory(date);
        verify(payslipWriter).getExistPayslipIds(date);
        verify(planRepository).findByDate(date);

        verifyNoMoreInteractions(payslipWriter, planRepository);
        verifyNoInteractions(payslipGenerator);
    }
}