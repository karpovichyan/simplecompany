package by.intexsoft.testproject.simplecompany.service.payslip.execute;

import java.io.IOException;
import java.util.List;

public interface PayslipGenerator {
    void generateResult(List<Integer> employeeActivityIds) throws IOException;
}
