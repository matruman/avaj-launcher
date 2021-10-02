package ru.avaj.matruman.aircrafts.items;

import ru.avaj.matruman.aircrafts.Coordinates;

public abstract class Aircraft {
    protected long id;
    protected String name;
    protected Coordinates coordinates;
    private static long idCounter = 0;

    protected Aircraft(String name, Coordinates coordinates) {
        this.id = nextId();
        this.name = name;
        this.coordinates = coordinates;
    }

    private static long nextId() {
        return ++Aircraft.idCounter;
    }
}
