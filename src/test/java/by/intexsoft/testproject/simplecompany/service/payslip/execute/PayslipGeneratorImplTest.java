package by.intexsoft.testproject.simplecompany.service.payslip.execute;

import by.intexsoft.testproject.simplecompany.entity.*;
import by.intexsoft.testproject.simplecompany.repository.EmployeeActivityRepository;
import by.intexsoft.testproject.simplecompany.service.payslip.PayslipContentGenerator;
import by.intexsoft.testproject.simplecompany.service.payslip.PayslipWriter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static by.intexsoft.testproject.simplecompany.entity.enumeration.ActivityType.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PayslipGeneratorImplTest {

    @Mock
    private PayslipWriter payslipWriter;

    @Mock
    private PayslipContentGenerator payslipContentGenerator;

    @Mock
    private EmployeeActivityRepository employeeActivityRepository;

    @InjectMocks
    private PayslipGeneratorImpl payslipGenerator;


    @Test
    @DisplayName("Should successfully generate payslips")
    void generateResultSuccess() throws IOException {
        LocalDate date = LocalDate.now();

        Activity activity1 = new Activity(1, PRESENT, BigDecimal.valueOf(1.0));
        Activity activity2 = new Activity(1, VACATION, BigDecimal.valueOf(0.8));
        Activity activity3 = new Activity(1, SICK_LEAVE, BigDecimal.valueOf(0.6));

        Position position = new Position(1, "Java Developer", 300);
        Contract contract = new Contract(1, LocalDate.parse("2021-01-01"));
        Employee employee = new Employee(1, "firstName", "lastName", position, contract);

        List<Integer> employeeActivityIds = new ArrayList<>();
        employeeActivityIds.add(1);
        employeeActivityIds.add(2);
        employeeActivityIds.add(3);

        Set<EmployeeActivity> employeeActivities = new HashSet<>();

        Plan plan = new Plan(1, date, 100, employeeActivities);

        EmployeeActivity employeeActivity1 = new EmployeeActivity(1, 20, employee, plan, activity1);
        EmployeeActivity employeeActivity2 = new EmployeeActivity(2, 20, employee, plan, activity2);
        EmployeeActivity employeeActivity3 = new EmployeeActivity(3, 20, employee, plan, activity3);

        employeeActivities.add(employeeActivity1);
        employeeActivities.add(employeeActivity2);
        employeeActivities.add(employeeActivity3);

        List<EmployeeActivity> convertedEmployeeActivities = new ArrayList<>(employeeActivities);

        ArrayList<Integer> absenceHours = new ArrayList<>();
        absenceHours.add(80);

        ArrayList<Integer> absenceReasonHours = new ArrayList<>();
        absenceReasonHours.add(20);
        absenceReasonHours.add(20);

        ArrayList<BigDecimal> earnedResult = new ArrayList<>();
        earnedResult.add(BigDecimal.valueOf(60.00).setScale(2, RoundingMode.CEILING));
        earnedResult.add(BigDecimal.valueOf(48.00).setScale(2, RoundingMode.CEILING));
        earnedResult.add(BigDecimal.valueOf(36.00).setScale(2, RoundingMode.CEILING));

        when(employeeActivityRepository.findAllById(employeeActivityIds)).thenReturn(convertedEmployeeActivities);
        when(payslipContentGenerator.generate(absenceHours, earnedResult, absenceReasonHours, employeeActivity1))
                .thenReturn("Test");
        doNothing().when(payslipWriter).create(employeeActivity1, "Test");

        payslipGenerator.generateResult(employeeActivityIds);

        verify(employeeActivityRepository).findAllById(employeeActivityIds);
        verify(payslipWriter).create(employeeActivity1, "Test");

        verifyNoMoreInteractions(employeeActivityRepository, payslipWriter, payslipContentGenerator);
    }
}