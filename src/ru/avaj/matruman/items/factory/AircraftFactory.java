package ru.avaj.matruman.items.factory;

import ru.avaj.matruman.exceptions.AvajLauncherException;
import ru.avaj.matruman.items.Coordinates;
import ru.avaj.matruman.items.Flyable;
import ru.avaj.matruman.items.aircrafts.Baloon;
import ru.avaj.matruman.items.aircrafts.Helicopter;
import ru.avaj.matruman.items.aircrafts.JetPlane;

public class AircraftFactory {
    public static Flyable newAircraft(String type, String name, int longitude, int latitude, int height) {

        if (longitude <= 0 || latitude <= 0 || height <= 0) {
            throw new AvajLauncherException("Coordinates must be positive numbers");
        }
        Coordinates coordinates = new Coordinates(longitude, latitude, height);

        switch (type.toLowerCase()) {
            case "baloon":
                return new Baloon(name, coordinates);
            case "helicopter":
                return new Helicopter(name, coordinates);
            case "jetplane":
                return new JetPlane(name, coordinates);
            default:
                throw new AvajLauncherException("Unknown aircraft type:" + type);
        }
    }
}
