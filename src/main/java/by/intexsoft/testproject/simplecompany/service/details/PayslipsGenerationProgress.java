package by.intexsoft.testproject.simplecompany.service.details;

import java.util.concurrent.atomic.AtomicInteger;

public class PayslipsGenerationProgress {
    private final AtomicInteger generatedPayslipsCount;
    private final Integer totalPayslips;

    public PayslipsGenerationProgress(AtomicInteger generatedPayslipsCount, Integer totalPayslips) {
        this.generatedPayslipsCount = generatedPayslipsCount;
        this.totalPayslips = totalPayslips;
    }

    public AtomicInteger getGeneratedPayslipsCount() {
        return generatedPayslipsCount;
    }

    public Integer getTotalPayslips() {
        return totalPayslips;
    }
}
