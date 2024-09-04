package com.example.backend.service;

import com.example.backend.model.WeatherEvent;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Slf4j
public class WeatherService {

    private final Random random = new Random();

    public WeatherEvent generateWeatherEvent() {
        String[] conditions = {"Clear", "Partly Cloudy", "Cloudy", "Rainy", "Stormy", "Snowy"};

        String location = "Location-" + random.nextInt(1000);
        String condition = conditions[random.nextInt(conditions.length)];
        double temperature = random.nextDouble() * 100 - 30;
        double windSpeed = random.nextDouble() * 20;

        return new WeatherEvent(location, condition, temperature, windSpeed);
    }
}
