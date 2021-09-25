package ru.avaj.matruman.transport;

public class Coordinates {
    private final int longitude;
    private final int latitude;
    private final int height;

    public Coordinates(int longitude, int latitude, int height) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.height = Math.min(height, 100);
    }

    public int getLongitude() {
        return longitude;
    }

    public int getLatitude() {
        return latitude;
    }

    public int getHeight() {
        return height;
    }
}
