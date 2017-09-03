package ru.bsa.test.generator.exception;

public class EventGeneratorException extends Exception {
    public EventGeneratorException(String message) {
        super(message);
    }

    public EventGeneratorException(String message, Throwable cause) {
        super(message, cause);
    }
}
