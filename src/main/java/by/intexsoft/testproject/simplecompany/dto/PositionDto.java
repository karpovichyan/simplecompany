package by.intexsoft.testproject.simplecompany.dto;

import javax.validation.constraints.NotNull;

public class PositionDto {
    private Integer id;
    @NotNull
    private String name;
    @NotNull
    private Integer salary;

    public PositionDto(Integer id, String name, Integer salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public PositionDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
