package by.intexsoft.testproject.simplecompany.service;

import by.intexsoft.testproject.simplecompany.dto.PayslipDto;

import java.io.IOException;

public interface PayslipService {
    void createPayslips(PayslipDto payslipDto) throws IOException;
}
