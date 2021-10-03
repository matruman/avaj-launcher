package ru.avaj.matruman.items;

import ru.avaj.matruman.weather.tower.WeatherTower;

public interface Flyable {
    void updateConditions();
    void registerTower(WeatherTower weatherTower);
}
