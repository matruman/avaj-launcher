package ru.avaj.matruman.aircrafts.items;

import ru.avaj.matruman.weather.tower.WeatherTower;

public interface Flyable {
    void updateConditions();
    void registerTower(WeatherTower weatherTower);
}
