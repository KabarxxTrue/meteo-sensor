package com.kabarxx.meteo_sensor.domain.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MeasurementDto {

    @NotNull(message = "Значение температуры не может быть пустым")
    @Min(value = -100, message = "Температура должна быть не менее -100")
    @Max(value = 100, message = "Температура должна быть не более 100")
    private Double value;

    @NotNull(message = "Поле 'raining' не должно быть пустым")
    private Boolean raining;

    @NotNull(message = "Сенсор не может быть пустым")
    private SensorDto sensor;

    public @NotNull(message = "Значение температуры не может быть пустым") @Min(value = -100, message = "Температура должна быть не менее -100") @Max(value = 100, message = "Температура должна быть не более 100") Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Boolean getRaining() {
        return raining;
    }

    public void setRaining(Boolean raining) {
        this.raining = raining;
    }

    public SensorDto getSensor() {
        return sensor;
    }

    public void setSensor(SensorDto sensor) {
        this.sensor = sensor;
    }
}
