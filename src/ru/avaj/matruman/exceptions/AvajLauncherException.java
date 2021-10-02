package ru.avaj.matruman.exceptions;

public class AvajLauncherException extends RuntimeException {

    public AvajLauncherException(String message) {
        super(message);
    }

    public AvajLauncherException(Throwable cause) {
        super(cause);
    }
}
