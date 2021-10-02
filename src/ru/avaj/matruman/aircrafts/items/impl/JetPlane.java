package ru.avaj.matruman.aircrafts.items.impl;

import ru.avaj.matruman.Writer;
import ru.avaj.matruman.aircrafts.items.Aircraft;
import ru.avaj.matruman.aircrafts.Coordinates;
import ru.avaj.matruman.aircrafts.items.Flyable;
import ru.avaj.matruman.weather.tower.WeatherTower;

public class JetPlane extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    public JetPlane(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateConditions() {
        String weather = weatherTower.getWeather(coordinates);
        String message = "";

        switch (weather) {
            case "SUN":
                message = "С'est ensoleillé. Il fait beau";
                coordinates = new Coordinates(
                        coordinates.getLongitude(),
                        coordinates.getLatitude() + 10,
                        coordinates.getHeight() + 2
                );
                break;
            case "RAIN":
                message = "Il pleut. Je n'aime pas ce temps";
                coordinates = new Coordinates(
                        coordinates.getLongitude(),
                        coordinates.getLatitude() + 5,
                        coordinates.getHeight()
                );
                break;
            case "FOG":
                message = "Nous sommes dans le brouillard";
                coordinates = new Coordinates(
                        coordinates.getLongitude(),
                        coordinates.getLatitude() + 1,
                        coordinates.getHeight()
                );
                break;
            case "SNOW":
                message = "Il neige. J'ai froid";
                coordinates = new Coordinates(
                        coordinates.getLongitude(),
                        coordinates.getLatitude(),
                        coordinates.getHeight() - 7
                );
                break;
        }
        Writer.write("JetPlane#" + name + "(" + id + "): " + message + ".\n");
        if (coordinates.getHeight() <= 0) {
            weatherTower.unregister(this);
            Writer.write("JetPlane#" + name + "(" + id + "): landing.\n");
            Writer.write("Tower says: JetPlane#" + name + "(" + id + ")" + " unregistered from weather tower.\n");
        }
    }

    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
        Writer.write("Tower says: JetPlane#" + name + "(" + id + ")" + " registered to weather tower.\n");
    }
}
