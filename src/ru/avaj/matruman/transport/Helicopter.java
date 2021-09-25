package ru.avaj.matruman.transport;

import ru.avaj.matruman.Writer;
import ru.avaj.matruman.weather.WeatherTower;

public class Helicopter extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    public Helicopter(String name, Coordinates coordinates) {
        super(name, coordinates);
    }
  
    @Override
    public void updateConditions() {
        String weather = weatherTower.getWeather(coordinates);
        String message = "";

        switch (weather) {
            case "SUN":
                coordinates = new Coordinates(
                        coordinates.getLongitude() + 10,
                        coordinates.getLatitude(),
                        coordinates.getHeight() + 2
                );
                message = "it is sunny outside. Let is go for a walk";
                break;
            case "RAIN":
                coordinates = new Coordinates(
                        coordinates.getLongitude() + 5,
                        coordinates.getLatitude(),
                        coordinates.getHeight()
                );
                message = "it's so cloudy outside. I am in a bad mood";
                break;
            case "FOG":
                coordinates = new Coordinates(
                        coordinates.getLongitude() + 1,
                        coordinates.getLatitude(),
                        coordinates.getHeight()
                );
                message = "it's foggy outside, I can't see anything";
                break;
            case "SNOW":
                coordinates = new Coordinates(
                        coordinates.getLongitude(),
                        coordinates.getLatitude(),
                        coordinates.getHeight() - 12
                );
                message = "There's a lot of snow outside";
                break;
        }
        Writer.write("Helicopter#" + name + "(" + id + "): " + message + ".\n");
        if (coordinates.getHeight() <= 0) {
            weatherTower.unregister(this);
            Writer.write("Helicopter#" + name + "(" + id + "): landing.\n");
            Writer.write("Tower says: Helicopter#" + name + "(" + id + ")" + " unregistered from weather tower.\n");
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
        Writer.write("Tower says: Helicopter#" + name + "(" + id + ")" + " registered to weather tower.\n");
    }
}
