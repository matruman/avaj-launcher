package ru.avaj.matruman;

import ru.avaj.matruman.exceptions.AvajLauncherException;
import ru.avaj.matruman.items.factory.AircraftFactory;
import ru.avaj.matruman.weather.tower.WeatherTower;

import java.io.*;
import java.util.Scanner;

public class Main {

    private static int ITERATIONS = 0;

    public static void main(String[] args) {
        try {
            if (args.length != 1 || args[0] == null) {
                throw new AvajLauncherException("Put scenario file as first argument");
            }
            WeatherTower weatherTower = getWeatherTower(args[0]);

            for (int i = 0; i < ITERATIONS; i++) {
                weatherTower.changeWeather();
            }
            Writer.close();
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static WeatherTower getWeatherTower(String fileName) throws FileNotFoundException {
        Scanner scanner = getScanner(fileName);

        if (!scanner.hasNextLine()) {
            throw new AvajLauncherException("File is empty");
        }
        int iterations = getIterations(scanner);

        WeatherTower weatherTower = new WeatherTower();
        int lineNumber = 1;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] arr = line.split(" ");
            ++lineNumber;

            if (arr.length == 1 && arr[0].length() == 0) {
                continue ;
            }
            if (arr.length != 5) {
                throw new AvajLauncherException("Wrong format in line " + lineNumber + ": " + line + "." +
                        " Required format: TYPE NAME LONGITUDE LATITUDE HEIGHT.");
            }
            try {
                AircraftFactory.newAircraft(
                        arr[0],
                        arr[1],
                        Integer.parseInt(arr[2]),
                        Integer.parseInt(arr[3]),
                        Integer.parseInt(arr[4])
                ).registerTower(weatherTower);
            }
            catch (NumberFormatException e) {
                throw new AvajLauncherException("Wrong number at line " + lineNumber + "\n" + e.getMessage());
            }
        }

        if (weatherTower.observersIsEmpty()) {
            throw new AvajLauncherException("Aircrafts aren't present in scenario file");
        }
        ITERATIONS = iterations;
        return weatherTower;
    }

    private static int getIterations(Scanner scanner) {
        try {
            int iterations = Integer.parseInt(scanner.nextLine());

            if (iterations <= 0) {
                throw new AvajLauncherException("Number of the iterations must be positive");
            }
            return iterations;
        }
        catch (NumberFormatException e) {
            throw new AvajLauncherException("First line must be number of iterations. " +
                    "Number must be a positive integer.");
        }
    }

    private static Scanner getScanner(String fileName) throws FileNotFoundException {
        File file = new File(fileName);

        if (!file.canRead()) {
            throw new AvajLauncherException("Cannot read the file: " + fileName);
        }
        return new Scanner(file);
    }
}
