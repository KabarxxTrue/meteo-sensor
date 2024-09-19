package com.kabarxx.meteo_sensor.services;

import com.kabarxx.meteo_sensor.domain.Measurement;
import com.kabarxx.meteo_sensor.domain.Sensor;
import com.kabarxx.meteo_sensor.domain.dto.MeasurementDto;
import com.kabarxx.meteo_sensor.exceptions.sensor.SensorNotFoundException;
import com.kabarxx.meteo_sensor.mappers.MeasurementMapper;
import com.kabarxx.meteo_sensor.repositories.MeasurementRepository;
import com.kabarxx.meteo_sensor.repositories.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MeasurementService {

    private final SensorRepository sensorRepository;
    private final MeasurementRepository measurementRepository;
    private final MeasurementMapper measurementMapper;

    @Autowired
    public MeasurementService(SensorRepository sensorRepository,
                              MeasurementRepository measurementRepository, MeasurementMapper measurementMapper) {
        this.sensorRepository = sensorRepository;
        this.measurementRepository = measurementRepository;
        this.measurementMapper = measurementMapper;
    }

    public void addMeasurement(MeasurementDto measurementDto) {
        Sensor sensor = sensorRepository.findByName(measurementDto.getSensor().getName())
                .orElseThrow(SensorNotFoundException::new);

        Measurement measurement = measurementMapper.toEntity(measurementDto);
        measurement.setSensor(sensor);
        measurement.setTimestamp(LocalDateTime.now());
        measurementRepository.save(measurement);
    }

    public List<MeasurementDto> getAllMeasurementsInfo() {
        List<Measurement> measurements = measurementRepository.findAll();

        return measurements.stream()
                .map(measurementMapper::toDto)
                .collect(Collectors.toList());
    }
}
