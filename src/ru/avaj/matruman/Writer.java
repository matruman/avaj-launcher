package ru.avaj.matruman;

import ru.avaj.matruman.exceptions.AvajException;

import java.io.FileWriter;
import java.io.IOException;

public class Writer {
    private final static String FILENAME = "simulation.txt";
    private static final FileWriter fileWriter;

    static {
        try {
            fileWriter = new FileWriter(FILENAME);
        } catch (IOException e) {
            throw new AvajException(e);
        }
    }

    public static void write(String str) {
        try {
            fileWriter.write(str);
        } catch (IOException e) {
            throw new AvajException(e);
        }
    }

    public static void close() {
        try {
            if (fileWriter != null) {
                fileWriter.flush();
                fileWriter.close();
            }
        } catch (IOException e) {
            throw new AvajException(e);
        }
    }
}
