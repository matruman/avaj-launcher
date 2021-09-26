package ru.avaj.matruman.transport.items;

import ru.avaj.matruman.weather.tower.WeatherTower;

public interface Flyable {
    void updateConditions();
    void registerTower(WeatherTower weatherTower);
}
