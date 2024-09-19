package com.kabarxx.meteo_sensor.mappers;

import com.kabarxx.meteo_sensor.domain.Measurement;
import com.kabarxx.meteo_sensor.domain.dto.MeasurementDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MeasurementMapper {

    MeasurementMapper INSTANCE = Mappers.getMapper(MeasurementMapper.class);

    MeasurementDto toDto(Measurement measurement);
    Measurement toEntity(MeasurementDto measurementDto);
}
