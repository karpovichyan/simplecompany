package by.intexsoft.testproject.simplecompany.service.payslip;

import by.intexsoft.testproject.simplecompany.entity.EmployeeActivity;

import java.util.ArrayList;

public interface PayslipContentGenerator {
    String generate(ArrayList<Integer> leaveHours, ArrayList<Double> leaveHoursRatio, EmployeeActivity employeeActivity);
}
