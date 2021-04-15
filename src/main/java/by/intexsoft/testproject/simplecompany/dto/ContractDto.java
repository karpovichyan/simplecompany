package by.intexsoft.testproject.simplecompany.dto;

import java.time.LocalDate;

public class ContractDto {
    private Integer id;
    private LocalDate date;

    public ContractDto(Integer id, LocalDate date) {
        this.id = id;
        this.date = date;
    }

    public ContractDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
