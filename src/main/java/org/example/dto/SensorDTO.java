package org.example.dto;

import jakarta.validation.constraints.NotEmpty;

public class SensorDTO {
    @NotEmpty(message = "Name should not be empty")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SensorDTO() {
    }
}
