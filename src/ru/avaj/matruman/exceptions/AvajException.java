package ru.avaj.matruman.exceptions;

public class AvajException extends RuntimeException {

    public AvajException(String message) {
        super(message);
    }

    public AvajException(Throwable cause) {
        super(cause);
    }
}
