package by.intexsoft.testproject.simplecompany.service.payslip;

import by.intexsoft.testproject.simplecompany.entity.EmployeeActivity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Service
public class FilePayslipContentGenerator implements PayslipContentGenerator {
    private final DateTimeFormatter MMMM_YYYY_FORMATTER = DateTimeFormatter.ofPattern("MMMM yyyy");

    @Override
    public String generate(ArrayList<Integer> absenceHours,
                           ArrayList<BigDecimal> earnedResults,
                           ArrayList<Integer> absenceReasonHours,
                           EmployeeActivity employeeActivity) {

        return String.format("Period               : %s\n" +
                        "First name           : %s\n" +
                        "Last name            : %s\n" +
                        "Position             : %s\n" +
                        "Salary               : %d\n" +
                        "Total hours          : %d\n" +
                        "Leave hours          : %d\n" +
                        "Leave hours by reason: %d\n" +
                        "***************************************\n" +
                        "Paid out   : %.2f",
                employeeActivity.getPlan().getDate().format(MMMM_YYYY_FORMATTER),
                employeeActivity.getEmployee().getFirstName(),
                employeeActivity.getEmployee().getLastName(),
                employeeActivity.getEmployee().getPosition().getName(),
                employeeActivity.getEmployee().getPosition().getSalary(),
                employeeActivity.getPlan().getTotalHours(),
                absenceHours.stream().mapToInt(Integer::intValue).sum(),
                absenceReasonHours.stream().mapToInt(Integer::intValue).sum(),
                getPaidOutInfo(earnedResults));
    }

    private BigDecimal getPaidOutInfo(ArrayList<BigDecimal> earnedResult) {
        return earnedResult
                .stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, BigDecimal.ROUND_UP);
    }
}
