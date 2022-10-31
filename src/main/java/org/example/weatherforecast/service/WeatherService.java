package org.example.weatherforecast.service;

import lombok.extern.slf4j.Slf4j;
import org.example.weatherforecast.domain.Location;
import org.example.weatherforecast.domain.Weather;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class WeatherService implements OptionalPipeline<Weather, Location> {

    @Override
    public Weather find(Location obj) {
        return null;
    }
}
