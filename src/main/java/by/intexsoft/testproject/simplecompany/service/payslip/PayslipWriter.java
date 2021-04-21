package by.intexsoft.testproject.simplecompany.service.payslip;

import by.intexsoft.testproject.simplecompany.entity.EmployeeActivity;

public interface PayslipWriter {
    void create(EmployeeActivity employeeActivity, String resultContent);
}
