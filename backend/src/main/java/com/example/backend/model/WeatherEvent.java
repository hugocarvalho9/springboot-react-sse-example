package com.example.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WeatherEvent {

    private String location;
    private String condition;
    private double temperature;
    private double windSpeed;
}
