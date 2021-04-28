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
    public String generate(ArrayList<Integer> hourLeaves,
                           ArrayList<BigDecimal> earnedResults,
                           ArrayList<Integer> reasonHourLeaves,
                           EmployeeActivity employeeActivity) {
        return "Period               : " + employeeActivity.getPlan().getDate().format(MMMM_YYYY_FORMATTER) + "\n"
                + "First name           : " + employeeActivity.getEmployee().getFirstName() + "\n"
                + "Last name            : " + employeeActivity.getEmployee().getLastName() + "\n"
                + "Position             : " + employeeActivity.getEmployee().getPosition().getName() + "\n"
                + "Salary               : " + employeeActivity.getEmployee().getPosition().getSalary() + "\n"
                + "Total hours          : " + employeeActivity.getPlan().getTotalHours() + "\n"
                + "Leave hours          : " + hourLeaves.stream().mapToInt(Integer::intValue).sum() + "\n"
                + "Leave hours by reason: " + reasonHourLeaves.stream().mapToInt(Integer::intValue).sum() + "\n"
                + "***************************************" + "\n"
                + "Paid out   : " + getPaidOutInfo(earnedResults);
    }

    private BigDecimal getPaidOutInfo(ArrayList<BigDecimal> earnedResult) {
        BigDecimal resultSalary = earnedResult.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        return resultSalary.setScale(2, BigDecimal.ROUND_UP);
    }
}
