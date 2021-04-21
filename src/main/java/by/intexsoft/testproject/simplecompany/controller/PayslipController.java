package by.intexsoft.testproject.simplecompany.controller;

import by.intexsoft.testproject.simplecompany.service.PayslipService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

@RestController
@RequestMapping("payslips")
public class PayslipController {
    private final PayslipService payslipService;

    public PayslipController(PayslipService payslipService) {
        this.payslipService = payslipService;
    }

    @PostMapping
    public void create(@Valid @RequestBody @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        payslipService.create(date);
    }

    @GetMapping
    public String getProgress(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return payslipService.getProgress(date);
    }
}
