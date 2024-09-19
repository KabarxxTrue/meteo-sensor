package com.kabarxx.meteo_sensor.exceptions.measurement;

public class MeasurementNotFoundException extends RuntimeException {

    public MeasurementNotFoundException() {
        super("Measurement not found");
    }
}
