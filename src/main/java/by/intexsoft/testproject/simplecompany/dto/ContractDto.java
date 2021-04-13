package by.intexsoft.testproject.simplecompany.dto;

import java.time.LocalDate;

public class ContractDto {
    private int id;
    private LocalDate date;

    public ContractDto(int id, LocalDate date) {
        this.id = id;
        this.date = date;
    }

    public ContractDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
