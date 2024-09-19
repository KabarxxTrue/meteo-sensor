package com.kabarxx.meteo_sensor.controllers;

import com.kabarxx.meteo_sensor.domain.dto.MeasurementDto;
import com.kabarxx.meteo_sensor.domain.dto.SensorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/execute")
    public void executeTest() {
        RestTemplate restTemplate = new RestTemplate();

        SensorDto newSensor = new SensorDto();
        newSensor.setName("TestSensor");

        String registerUrl = "http://localhost:8080/sensors/registration";
        ResponseEntity<String> response = restTemplate.postForEntity(registerUrl, newSensor, String.class);

        if (response.getStatusCode() == HttpStatus.OK)
            System.out.println("Sensor registered successfully.");

        Random random = new Random();

        for (int i = 0; i < 1000; i++) {
            MeasurementDto measurementDto = new MeasurementDto();
            double randomTemperature = -100 + (200 * random.nextDouble());
            measurementDto.setValue(randomTemperature);
            measurementDto.setRaining(random.nextBoolean());
            measurementDto.setSensor(newSensor);

            String addMeasurementUrl = "http://localhost:8080/measurements/add";
            restTemplate.postForEntity(addMeasurementUrl, measurementDto, String.class);
        }

        String getMeasurementsUrl = "http://localhost:8080/measurements";
        ResponseEntity<MeasurementDto[]> measurementsResponse = restTemplate.getForEntity(getMeasurementsUrl, MeasurementDto[].class);

        MeasurementDto[] measurements = measurementsResponse.getBody();

        if (measurements != null) {
            System.out.println("Number of measurements received: " + measurements.length);
            for (MeasurementDto measurement : measurements) {
                System.out.println(measurement);
            }
        } else {
            System.out.println("No measurements found.");
        }
    }
}
