package by.intexsoft.testproject.simplecompany.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Objects;

@Entity
public class JobInfo {

    @Id
    private String position;

    @Column
    private int salary;

    @OneToOne(mappedBy = "jobInfo")
    private Employee employee;

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobInfo jobInfo = (JobInfo) o;
        return position.equals(jobInfo.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }
}
