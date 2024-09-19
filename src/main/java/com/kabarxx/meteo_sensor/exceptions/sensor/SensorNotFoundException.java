package com.kabarxx.meteo_sensor.exceptions.sensor;

public class SensorNotFoundException extends RuntimeException {

    public SensorNotFoundException() {
        super("Sensor not found");
    }
}
