package by.intexsoft.testproject.simplecompany.exception;

import java.io.IOException;

public class DirectoryException extends RuntimeException {
    public DirectoryException(String message, IOException exception) {
        super(message);
    }
}
