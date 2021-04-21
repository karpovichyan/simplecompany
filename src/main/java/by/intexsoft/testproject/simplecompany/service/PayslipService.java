package by.intexsoft.testproject.simplecompany.service;

import java.io.IOException;
import java.time.LocalDate;

public interface PayslipService {
    void create(LocalDate date) throws IOException;
}
