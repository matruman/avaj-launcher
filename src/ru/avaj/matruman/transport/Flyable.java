package ru.avaj.matruman.transport;

import ru.avaj.matruman.weather.WeatherTower;

public interface Flyable {
    void updateConditions();
    void registerTower(WeatherTower weatherTower);
}
