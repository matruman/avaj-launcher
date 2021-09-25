package ru.avaj.matruman.weather;

import ru.avaj.matruman.transport.Coordinates;

import java.util.Random;

public class WeatherProvider {
    private static WeatherProvider weatherProvider;
    private static final String[] weather = {"RAIN", "FOG", "SUN", "SNOW"};

    private WeatherProvider() {
    }

    public static WeatherProvider getProvider() {
        if (weatherProvider == null) {
            weatherProvider = new WeatherProvider();
        }
        return weatherProvider;
    }

    public String getCurrentWeather(Coordinates coordinates) {
        int randInt = new Random().nextInt();

        return weather[Math.abs(randInt) % weather.length];
    }
}