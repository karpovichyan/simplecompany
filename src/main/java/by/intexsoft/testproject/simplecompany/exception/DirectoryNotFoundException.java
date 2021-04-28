package by.intexsoft.testproject.simplecompany.exception;

import java.io.IOException;

public class DirectoryNotFoundException extends RuntimeException {
    public DirectoryNotFoundException(String message, IOException exception) {
        super(message);
    }
}
