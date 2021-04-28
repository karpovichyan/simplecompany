package by.intexsoft.testproject.simplecompany.exception;

import java.io.IOException;

public class PayslipNotCreatedException extends RuntimeException {
    public PayslipNotCreatedException(String message, IOException exception) {
        super(message);
    }
}
