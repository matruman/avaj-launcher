package ru.avaj.matruman.aircrafts.factory;

import ru.avaj.matruman.exceptions.AvajLauncherException;
import ru.avaj.matruman.aircrafts.Coordinates;
import ru.avaj.matruman.aircrafts.items.Flyable;
import ru.avaj.matruman.aircrafts.items.impl.Baloon;
import ru.avaj.matruman.aircrafts.items.impl.Helicopter;
import ru.avaj.matruman.aircrafts.items.impl.JetPlane;

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
