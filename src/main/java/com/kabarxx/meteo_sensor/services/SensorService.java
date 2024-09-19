package com.kabarxx.meteo_sensor.services;

import com.kabarxx.meteo_sensor.domain.Sensor;
import com.kabarxx.meteo_sensor.domain.dto.SensorDto;
import com.kabarxx.meteo_sensor.exceptions.sensor.SensorAlreadyExistsException;
import com.kabarxx.meteo_sensor.mappers.SensorMapper;
import com.kabarxx.meteo_sensor.repositories.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SensorService {

    private final SensorRepository sensorRepository;
    private final SensorMapper sensorMapper;

    @Autowired
    public SensorService(SensorRepository sensorRepository, SensorMapper sensorMapper) {
        this.sensorRepository = sensorRepository;
        this.sensorMapper = sensorMapper;
    }

    public void registerNewSensor(SensorDto sensorDto) {
        if (sensorRepository.findByName(sensorDto.getName()).isPresent())
            throw new SensorAlreadyExistsException();

        Sensor sensor = sensorMapper.toEntity(sensorDto);
        sensorRepository.save(sensor);
    }
}
