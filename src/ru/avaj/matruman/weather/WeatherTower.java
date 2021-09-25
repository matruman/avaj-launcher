package ru.avaj.matruman.weather;

import ru.avaj.matruman.transport.Coordinates;

public class WeatherTower extends Tower {

    public String getWeather(Coordinates coordinates) {
        return WeatherProvider.getProvider().getCurrentWeather(coordinates);
    }

    public void changeWeather() {
        conditionChanged();
    }
}
