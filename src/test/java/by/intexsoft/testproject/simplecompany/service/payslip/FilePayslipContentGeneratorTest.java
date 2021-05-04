package by.intexsoft.testproject.simplecompany.service.payslip;

import by.intexsoft.testproject.simplecompany.entity.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static by.intexsoft.testproject.simplecompany.entity.enumeration.ActivityType.PRESENT;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class FilePayslipContentGeneratorTest {

    @Test
    @DisplayName("Should return string with payslip info")
    void generateSuccess() {
        LocalDate date = LocalDate.now();

        Activity activity = new Activity(1, PRESENT, BigDecimal.valueOf(1.0));

        Position position = new Position(1, "Java Developer", 300);
        Contract contract = new Contract(1, LocalDate.parse("2021-01-01"));
        Employee employee = new Employee(1, "firstName", "lastName", position, contract);

        Set<EmployeeActivity> employeeActivities = new HashSet<>();
        Plan plan = new Plan(1, date, 100, employeeActivities);

        EmployeeActivity employeeActivity = new EmployeeActivity(1, 20, employee, plan, activity);
        employeeActivities.add(employeeActivity);

        ArrayList<Integer> absenceHours = new ArrayList<>();
        absenceHours.add(80);

        ArrayList<BigDecimal> earnedResults = new ArrayList<>();
        earnedResults.add(BigDecimal.valueOf(60.00).setScale(2, RoundingMode.CEILING));
        earnedResults.add(BigDecimal.valueOf(48.00).setScale(2, RoundingMode.CEILING));
        earnedResults.add(BigDecimal.valueOf(36.00).setScale(2, RoundingMode.CEILING));

        ArrayList<Integer> absenceReasonHours = new ArrayList<>();
        absenceReasonHours.add(20);
        absenceReasonHours.add(20);

        FilePayslipContentGenerator filePayslipContentGenerator = new FilePayslipContentGenerator();

        String expectedResult = String.format("Period               : %s\n" +
                        "First name           : %s\n" +
                        "Last name            : %s\n" +
                        "Position             : %s\n" +
                        "Salary               : %d\n" +
                        "Total hours          : %d\n" +
                        "Leave hours          : %d\n" +
                        "Leave hours by reason: %d\n" +
                        "***************************************\n" +
                        "Paid out   : %.2f",
                date.format(DateTimeFormatter.ofPattern("MMMM yyyy")),
                employeeActivity.getEmployee().getFirstName(),
                employeeActivity.getEmployee().getLastName(),
                employeeActivity.getEmployee().getPosition().getName(),
                employeeActivity.getEmployee().getPosition().getSalary(),
                employeeActivity.getPlan().getTotalHours(),
                absenceHours.stream().mapToInt(Integer::intValue).sum(),
                absenceReasonHours.stream().mapToInt(Integer::intValue).sum(),
                earnedResults.stream().reduce(BigDecimal.ZERO, BigDecimal::add).setScale(2, BigDecimal.ROUND_UP));

        assertEquals(expectedResult,
                filePayslipContentGenerator.generate(absenceHours, earnedResults, absenceReasonHours, employeeActivity));

    }
}