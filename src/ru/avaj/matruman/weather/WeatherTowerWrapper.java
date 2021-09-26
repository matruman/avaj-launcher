package ru.avaj.matruman.weather;

import ru.avaj.matruman.weather.tower.WeatherTower;

public class WeatherTowerWrapper {
    private final int iterations;
    private final WeatherTower weatherTower;

    public WeatherTowerWrapper(int iterations, WeatherTower weatherTower) {
        this.iterations = iterations;
        this.weatherTower = weatherTower;
    }

    public int getIterations() {
        return iterations;
    }

    public WeatherTower getWeatherTower() {
        return weatherTower;
    }
}
