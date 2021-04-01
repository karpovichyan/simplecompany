package by.intexsoft.testproject.simplecompany.dto;

public class ActivityDto {
    private int id;
    private int hours;
    private String type;

    public ActivityDto(int hours, String type) {
        this.hours = hours;
        this.type = type;
    }

    public ActivityDto() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }
}
