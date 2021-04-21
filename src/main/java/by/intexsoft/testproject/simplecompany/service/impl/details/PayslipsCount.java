package by.intexsoft.testproject.simplecompany.service.impl.details;

import java.util.concurrent.atomic.AtomicInteger;

public class PayslipsCount {
    private AtomicInteger count;
    private Integer totalEmployees;

    public PayslipsCount(AtomicInteger count, Integer totalEmployees) {
        this.count = count;
        this.totalEmployees = totalEmployees;
    }

    public AtomicInteger getCount() {
        return count;
    }

    public void setCount(AtomicInteger count) {
        this.count = count;
    }

    public Integer getTotalEmployees() {
        return totalEmployees;
    }

    public void setTotalEmployees(Integer totalEmployees) {
        this.totalEmployees = totalEmployees;
    }
}
