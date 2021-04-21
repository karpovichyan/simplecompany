package by.intexsoft.testproject.simplecompany.service.payslip;

import by.intexsoft.testproject.simplecompany.entity.EmployeeActivity;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.stream.DoubleStream;

@Service
public class FilePayslipContentGenerator implements PayslipContentGenerator {
    private final DateTimeFormatter MMMM_YYYY_FORMATTER = DateTimeFormatter.ofPattern("MMMM yyyy");

    @Override
    public String generate(ArrayList<Integer> leaveHours, ArrayList<Double> leaveHoursRatio, EmployeeActivity employeeActivity) {
        return "Period     : " + employeeActivity.getPlan().getDate().format(MMMM_YYYY_FORMATTER) + "\n"
                + "First name : " + employeeActivity.getEmployee().getFirstName() + "\n"
                + "Last name  : " + employeeActivity.getEmployee().getLastName() + "\n"
                + "Position   : " + employeeActivity.getEmployee().getPosition().getName() + "\n"
                + "Salary     : " + employeeActivity.getEmployee().getPosition().getSalary() + "\n"
                + "Total hours: " + employeeActivity.getPlan().getTotalHours() + "\n"
                + "Leave hours: " + leaveHours.stream().mapToInt(Integer::intValue).sum() + "\n"
                + "***************************************" + "\n"
                + "Paid out   : " + paidOutInfo(leaveHoursRatio);
    }

    private double paidOutInfo(ArrayList<Double> leaveHoursRatio) {
        double resultSalary = leaveHoursRatio.stream().flatMapToDouble(DoubleStream::of).sum();
        return Math.round(resultSalary * 100) / 100D;
    }
}
