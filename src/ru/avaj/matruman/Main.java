package ru.avaj.matruman;

import ru.avaj.matruman.exceptions.AvajLauncherException;
import ru.avaj.matruman.transport.AircraftFactory;
import ru.avaj.matruman.weather.WeatherTower;
import ru.avaj.matruman.weather.WeatherTowerWrapper;

import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            if (args.length != 1 || args[0] == null) {
                throw new AvajLauncherException("put scenario file as first arg");
            }
            WeatherTowerWrapper wrapper = getWrapper(args[0]);

            for (int i = 0; i < wrapper.getIterations(); i++) {
                wrapper.getWeatherTower().changeWeather();
            }
            Writer.close();
        }
        catch (AvajLauncherException e) {
            System.out.println("Error: " + e.getMessage());
        }
        catch (Exception e) {
            System.out.println("Unknown error: " + e.getMessage());
        }
    }

    private static WeatherTowerWrapper getWrapper(String fileName) throws FileNotFoundException {
        Scanner scanner = getScanner(fileName);

        if (!scanner.hasNextLine()) {
            throw new AvajLauncherException("File is empty");
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
                throw new AvajLauncherException("Wrong number in line " + lineIndex + ": " + line + "." +
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
                throw new AvajLauncherException("Wrong number in line " + lineIndex + ": " + line + "." +
                        " Current format: TYPE NAME LONGITUDE LATITUDE HEIGHT.");
            }
        }

        if (weatherTower.observersIsEmpty()) {
            throw new AvajLauncherException("Aircrafts dont present in scenario file");
        }
        return new WeatherTowerWrapper(iterations, weatherTower);
    }

    private static int getIterations(Scanner scanner) {
        try {
            int iterations = Integer.parseInt(scanner.nextLine());

            if (iterations <= 0) {
                throw new AvajLauncherException("Number of the iteration must be positive");
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
            throw new AvajLauncherException("cant read file: " + fileName);
        }
        return new Scanner(file);
    }
}
