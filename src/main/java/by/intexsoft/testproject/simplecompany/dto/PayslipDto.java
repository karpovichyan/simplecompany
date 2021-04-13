package by.intexsoft.testproject.simplecompany.dto;

import java.time.LocalDate;

public class PayslipDto {
    private LocalDate date;

    public PayslipDto(LocalDate date) {
        this.date = date;
    }

    public PayslipDto() {
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
