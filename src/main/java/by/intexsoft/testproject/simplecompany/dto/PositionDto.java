package by.intexsoft.testproject.simplecompany.dto;

public class PositionDto {
    private int id;
    private String name;
    private int salary;

    public PositionDto(int id, String name, int salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public PositionDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
