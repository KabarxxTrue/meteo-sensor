package com.kabarxx.meteo_sensor.exceptions.sensor;

public class SensorAlreadyExistsException extends RuntimeException {

    public SensorAlreadyExistsException() {
        super("Sensor already exists");
    }
}
