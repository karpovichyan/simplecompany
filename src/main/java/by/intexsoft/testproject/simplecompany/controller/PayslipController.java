package by.intexsoft.testproject.simplecompany.controller;

import by.intexsoft.testproject.simplecompany.dto.PayslipDto;
import by.intexsoft.testproject.simplecompany.service.PayslipService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/payslips")
public class PayslipController {
    private final PayslipService payslipService;

    public PayslipController(PayslipService payslipService) {
        this.payslipService = payslipService;
    }

    @PostMapping
    void createPayslip(@RequestBody PayslipDto payslipDto) throws IOException {
        payslipService.createPayslips(payslipDto);
    }
}
