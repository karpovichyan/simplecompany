package by.intexsoft.testproject.simplecompany.service.payslip;

import by.intexsoft.testproject.simplecompany.entity.EmployeeActivity;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface PayslipWriter {
    void create(EmployeeActivity employeeActivity, String resultContent) throws IOException;

    List<String> getExistPayslipIds(LocalDate date) throws IOException;

    void createDirectory(LocalDate date) throws IOException;
}