package by.intexsoft.testproject.simplecompany.service.payslip;

import by.intexsoft.testproject.simplecompany.entity.EmployeeActivity;

import java.io.IOException;

public interface PayslipWriter {
    void makePayslip(EmployeeActivity employeeActivity, String resultContent) throws IOException;
}
