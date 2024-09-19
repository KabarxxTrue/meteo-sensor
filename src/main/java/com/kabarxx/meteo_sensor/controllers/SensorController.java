package com.kabarxx.meteo_sensor.controllers;

import com.kabarxx.meteo_sensor.domain.dto.SensorDto;
import com.kabarxx.meteo_sensor.exceptions.sensor.SensorAlreadyExistsException;
import com.kabarxx.meteo_sensor.services.SensorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/sensors")
public class SensorController {

    private final SensorService sensorService;

    @Autowired
    public SensorController(final SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @PostMapping("/registration")
    public ResponseEntity<String> registerSensor(@Valid @RequestBody SensorDto sensorDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMessage = Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage();
            return ResponseEntity.badRequest().body(errorMessage);
        }

        try {
            sensorService.registerNewSensor(sensorDto);
            return ResponseEntity.ok("Сенсор успешно зарегистрирован");
        } catch (SensorAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}
