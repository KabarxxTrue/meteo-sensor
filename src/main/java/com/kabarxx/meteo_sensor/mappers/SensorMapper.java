package com.kabarxx.meteo_sensor.mappers;

import com.kabarxx.meteo_sensor.domain.Sensor;
import com.kabarxx.meteo_sensor.domain.dto.SensorDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SensorMapper {

    SensorDto toDto(Sensor sensor);
    Sensor toEntity(SensorDto sensorDto);
}
