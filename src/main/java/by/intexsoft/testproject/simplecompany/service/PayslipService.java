package by.intexsoft.testproject.simplecompany.service;

import java.time.LocalDate;

public interface PayslipService {
    void create(LocalDate date);

    String getProgress(LocalDate date);
}
