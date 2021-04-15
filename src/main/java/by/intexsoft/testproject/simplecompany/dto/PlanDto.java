package by.intexsoft.testproject.simplecompany.dto;

import java.time.LocalDate;

public class PlanDto {
    private Integer id;
    private LocalDate date;
    private Integer totalHours;

    public PlanDto(Integer id, LocalDate date, Integer totalHours) {
        this.id = id;
        this.date = date;
        this.totalHours = totalHours;
    }

    public PlanDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTotalHours(Integer totalHours) {
        this.totalHours = totalHours;
    }

    public Integer getTotalHours() {
        return totalHours;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
