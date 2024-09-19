package com.kabarxx.meteo_sensor.repositories;

import com.kabarxx.meteo_sensor.domain.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Long> {
}
