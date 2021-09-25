package ru.avaj.matruman.transport;

import ru.avaj.matruman.Writer;
import ru.avaj.matruman.weather.WeatherTower;

public class Baloon extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    public Baloon(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateConditions() {
        String weather = weatherTower.getWeather(coordinates);
        String message = "";

        switch (weather) {
            case "SUN":
                coordinates = new Coordinates(
                        coordinates.getLongitude() + 2,
                        coordinates.getLatitude(),
                        coordinates.getHeight() + 4
                );
                message = "it's sunny now";
                break;
            case "RAIN":
                coordinates = new Coordinates(
                        coordinates.getLongitude(),
                        coordinates.getLatitude(),
                        coordinates.getHeight() - 5
                );
                message = "it's raining now";
                break;
            case "FOG":
                coordinates = new Coordinates(
                        coordinates.getLongitude(),
                        coordinates.getLatitude(),
                        coordinates.getHeight() - 3
                );
                message = "we are in the fog";
                break;
            case "SNOW":
                coordinates = new Coordinates(
                        coordinates.getLongitude(),
                        coordinates.getLatitude(),
                        coordinates.getHeight() - 15
                );
                message = "now it is snowing";
                break;
        }
        Writer.write("Baloon#" + name + "(" + id + "): " + message + ".\n");
        if (coordinates.getHeight() <= 0) {
            weatherTower.unregister(this);
            Writer.write("Baloon#" + name + "(" + id + "): landing.\n");
            Writer.write("Tower says: Baloon#" + name + "(" + id + ")" + " unregistered from weather tower.\n");
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
        Writer.write("Tower says: Baloon#" + name + "(" + id + ")" + " registered to weather tower.\n");
    }
}
