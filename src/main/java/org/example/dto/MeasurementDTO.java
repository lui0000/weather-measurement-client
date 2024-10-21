package org.example.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import java.time.LocalDateTime;

public class MeasurementDTO {

    @Min(value = -100, message = "Temperature should not be less than -100")
    @Max(value = 100, message = "Temperature should not be more than 100")
    private double temperature;

    private boolean raining;

    private SensorDTO sensor;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    public MeasurementDTO() {

    }


    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    public SensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(SensorDTO sensor) {
        this.sensor = sensor;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public MeasurementDTO(double temperature, boolean raining, SensorDTO sensor) {
        this.temperature = temperature;
        this.raining = raining;
        this.sensor = sensor;
    }
}
