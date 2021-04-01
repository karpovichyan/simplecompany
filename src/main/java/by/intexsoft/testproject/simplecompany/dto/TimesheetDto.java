package by.intexsoft.testproject.simplecompany.dto;

public class TimesheetDto {
    private int id;
    private int month;
    private int year;
    private int totalHours;

    public TimesheetDto(int month, int year, int totalHours) {
        this.month = month;
        this.year = year;
        this.totalHours = totalHours;
    }

    public TimesheetDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(int totalHours) {
        this.totalHours = totalHours;
    }
}
