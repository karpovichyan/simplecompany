package by.intexsoft.testproject.simplecompany.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private LocalDate date;

    @OneToOne(mappedBy = "contract", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Employee employee;

    public Contract(LocalDate date) {
        this.date = date;
    }

    public Contract() {
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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contract contract = (Contract) o;
        return id == contract.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
