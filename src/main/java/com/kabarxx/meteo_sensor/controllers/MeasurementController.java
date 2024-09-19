package com.kabarxx.meteo_sensor.controllers;

import com.kabarxx.meteo_sensor.domain.dto.MeasurementDto;
import com.kabarxx.meteo_sensor.exceptions.measurement.MeasurementNotFoundException;
import com.kabarxx.meteo_sensor.exceptions.sensor.SensorNotFoundException;
import com.kabarxx.meteo_sensor.services.MeasurementService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {

    private final MeasurementService measurementService;

    @Autowired
    public MeasurementController(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addMeasurement(@Valid @RequestBody MeasurementDto measurementDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMessage = Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage();
            return ResponseEntity.badRequest().body(errorMessage);
        }

        try {
            measurementService.addMeasurement(measurementDto);
            return ResponseEntity.ok("Измерение успешно добавлено");
        } catch (SensorNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<MeasurementDto>> getAllMeasurements() {
        try {
            List<MeasurementDto> measurements = measurementService.getAllMeasurementsInfo();
            return ResponseEntity.ok(measurements);
        } catch (MeasurementNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
