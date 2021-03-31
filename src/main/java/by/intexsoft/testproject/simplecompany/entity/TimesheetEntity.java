package by.intexsoft.testproject.simplecompany.entity;

import javax.persistence.*;

@Entity
@Table(name = "timesheet")
public class TimesheetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private int month;

    @Column
    private int year;

    @Column
    private int total_hours;

    public TimesheetEntity(int month, int year, int total_hours) {
        this.month = month;
        this.year = year;
        this.total_hours = total_hours;
    }

    public TimesheetEntity() {
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

    public int getTotal_hours() {
        return total_hours;
    }

    public void setTotal_hours(int total_hours) {
        this.total_hours = total_hours;
    }
}
