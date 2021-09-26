package ru.avaj.matruman.weather.tower;

import ru.avaj.matruman.transport.Coordinates;
import ru.avaj.matruman.weather.provider.WeatherProvider;

public class WeatherTower extends Tower {

    public String getWeather(Coordinates coordinates) {
        return WeatherProvider.getProvider().getCurrentWeather(coordinates);
    }

    public void changeWeather() {
        conditionChanged();
    }
}
