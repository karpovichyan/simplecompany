package by.intexsoft.testproject.simplecompany.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PositionDto {
    private Integer id;
    @NotBlank
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }
}
