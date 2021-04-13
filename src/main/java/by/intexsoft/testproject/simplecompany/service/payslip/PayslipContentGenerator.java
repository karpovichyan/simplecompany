package by.intexsoft.testproject.simplecompany.service.payslip;

import by.intexsoft.testproject.simplecompany.entity.EmployeeActivity;

import java.math.BigDecimal;
import java.util.ArrayList;

public interface PayslipContentGenerator {
    String generate(ArrayList<Integer> leaveHours, ArrayList<BigDecimal> leaveHoursRatio, EmployeeActivity employeeActivity);
}
