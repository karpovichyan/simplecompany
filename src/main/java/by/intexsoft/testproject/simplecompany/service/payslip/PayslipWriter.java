package by.intexsoft.testproject.simplecompany.service.payslip;

import by.intexsoft.testproject.simplecompany.entity.Employee;
import by.intexsoft.testproject.simplecompany.entity.EmployeeActivity;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface PayslipWriter {
    void create(EmployeeActivity employeeActivity, String resultContent) throws IOException;

    List<Employee> getExistPayslips(LocalDate date, Set<Employee> allEmployees) throws IOException;
}
