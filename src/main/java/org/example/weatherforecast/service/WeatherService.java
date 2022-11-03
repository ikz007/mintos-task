package org.example.weatherforecast.service;

import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.example.weatherforecast.domain.Location;
import org.example.weatherforecast.domain.Weather;
import org.example.weatherforecast.model.WeatherStatus;
import org.example.weatherforecast.repository.WeatherRepository;
import org.example.weatherforecast.util.WeatherConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Component
@Slf4j
public class WeatherService implements OptionalPipeline<Weather, Location> {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private WeatherRepository weatherRepository;

    @Override
    @Cacheable(cacheNames = WeatherConstants.WEATHER_CACHE_NAME, key = WeatherConstants.WEATHER_CACHE_KEY)
    @Retry(name = WeatherConstants.WEATHER_RETRY)
    public Weather find(Location obj) {
        String url = "https://api.open-meteo.com/v1/forecast?latitude=" + obj.getLatitude()
                +"&longitude="+ obj.getLongitude()
                +"&current_weather=true&hourly=temperature_2m";
        ResponseEntity<String> res = restTemplate.getForEntity(url, String.class);
        Weather weather;
        if(res.getStatusCode() == HttpStatus.OK) {
            weather = new Weather(res.getBody(), obj, LocalDateTime.now());
        } else {
            weather = new Weather(LocalDateTime.now(), WeatherStatus.Fail, obj);
        }
        weatherRepository.save(weather);
        return weather;
    }
}
