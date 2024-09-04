package com.example.backend.controller;

import com.example.backend.model.WeatherEvent;
import com.example.backend.service.WeatherService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RestController
@AllArgsConstructor
@Slf4j
public class WeatherNotificationController {

    private static final Duration EVENT_INTERVAL = Duration.ofSeconds(5);

    private final WeatherService weatherService;

    @GetMapping("/stream-sse")
    public Flux<ServerSentEvent<WeatherEvent>> streamEvents() {
        return Flux.interval(EVENT_INTERVAL)
                .map(sequence -> {
                    var event = weatherService.generateWeatherEvent();
                    log.debug("Generated event: {}", event);
                    return ServerSentEvent.<WeatherEvent>builder()
                            .id(String.valueOf(sequence))
                            .event("message")
                            .data(event)
                            .build();
                });
    }
}
