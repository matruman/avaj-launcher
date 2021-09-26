package ru.avaj.matruman.transport.factory;

import ru.avaj.matruman.exceptions.AvajException;
import ru.avaj.matruman.transport.Coordinates;
import ru.avaj.matruman.transport.items.Flyable;
import ru.avaj.matruman.transport.items.impl.Baloon;
import ru.avaj.matruman.transport.items.impl.Helicopter;
import ru.avaj.matruman.transport.items.impl.JetPlane;

public class AircraftFactory {
    public static Flyable newAircraft(String type, String name, int longitude, int latitude, int height) {

        if (longitude <= 0 || latitude <= 0 || height <= 0) {
            throw new AvajException("Coordinates must be positive numbers");
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
                throw new AvajException("Unknown aircraft type:" + type);
        }
    }
}
