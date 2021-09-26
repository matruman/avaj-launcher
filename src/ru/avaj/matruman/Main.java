package ru.avaj.matruman;

import ru.avaj.matruman.exceptions.AvajException;
import ru.avaj.matruman.transport.factory.AircraftFactory;
import ru.avaj.matruman.weather.tower.WeatherTower;

import java.io.*;
import java.util.Scanner;

public class Main {

    private static int ITERATIONS = 0;

    public static void main(String[] args) {
        try {
            if (args.length != 1 || args[0] == null) {
                throw new AvajException("Put scenario file as first argument");
            }
            WeatherTower weatherTower = getWeatherTower(args[0]);

            for (int i = 0; i < ITERATIONS; i++) {
                weatherTower.changeWeather();
            }
            Writer.close();
        }
        catch (AvajException e) {
            System.out.println("Error: " + e.getMessage());
        }
        catch (Exception e) {
            System.out.println("Unknown error: " + e.getMessage());
        }
    }

    private static WeatherTower getWeatherTower(String fileName) throws FileNotFoundException {
        Scanner scanner = getScanner(fileName);

        if (!scanner.hasNextLine()) {
            throw new AvajException("File is empty");
        }
        int iterations = getIterations(scanner);

        WeatherTower weatherTower = new WeatherTower();
        int lineIndex = 1;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] arr = line.split(" ");
            ++lineIndex;

            if (arr.length == 1 && arr[0].length() == 0) {
                continue ;
            }
            if (arr.length != 5) {
                throw new AvajException("Wrong number in line " + lineIndex + ": " + line + "." +
                        " Current format: TYPE NAME LONGITUDE LATITUDE HEIGHT.");
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
                throw new AvajException("Wrong number in line " + lineIndex + ": " + line + "." +
                        " Current format: " +
                        "TYPE NAME LONGITUDE LATITUDE HEIGHT.");
            }
        }

        if (weatherTower.observersIsEmpty()) {
            throw new AvajException("Aircrafts isn't present in scenario file");
        }
        ITERATIONS = iterations;
        return weatherTower;
    }

    private static int getIterations(Scanner scanner) {
        try {
            int iterations = Integer.parseInt(scanner.nextLine());

            if (iterations <= 0) {
                throw new AvajException("Number of the iterations must be positive");
            }
            return iterations;
        }
        catch (NumberFormatException e) {
            throw new AvajException("First line must be number of iterations. " +
                    "Number must be a positive integer.");
        }
    }

    private static Scanner getScanner(String fileName) throws FileNotFoundException {
        File file = new File(fileName);

        if (!file.canRead()) {
            throw new AvajException("Cannot read the file: " + fileName);
        }
        return new Scanner(file);
    }
}
