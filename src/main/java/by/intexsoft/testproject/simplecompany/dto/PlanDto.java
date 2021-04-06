package by.intexsoft.testproject.simplecompany.dto;

import java.time.LocalDate;

public class PlanDto {
    private int id;
    private LocalDate date;
    private int totalHours;

    public PlanDto(int id, LocalDate date, int totalHours) {
        this.id = id;
        this.date = date;
        this.totalHours = totalHours;
    }

    public PlanDto() {
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

    public int getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(int totalHours) {
        this.totalHours = totalHours;
    }
}
