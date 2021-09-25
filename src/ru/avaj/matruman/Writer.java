package ru.avaj.matruman;

import ru.avaj.matruman.exceptions.AvajLauncherException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {
    private final static String SIMULATION_FILENAME = "simulation.txt";
    private static FileWriter fileWriter;

    public static void write(String str) {
        try {
            if (fileWriter == null) {
                fileWriter = new FileWriter(new File(SIMULATION_FILENAME));
            }
            fileWriter.write(str);
        }
        catch (IOException e) {
            throw new AvajLauncherException(e);
        }
    }

    public static void close() {
        try {
            if (fileWriter != null) {
                fileWriter.flush();
                fileWriter.close();
            }
        }
        catch (IOException e) {
            throw new AvajLauncherException(e);
        }
    }
}
