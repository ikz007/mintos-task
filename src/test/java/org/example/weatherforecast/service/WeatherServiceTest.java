package org.example.weatherforecast.service;

import org.example.weatherforecast.domain.Location;
import org.example.weatherforecast.domain.Weather;
import org.example.weatherforecast.repository.WeatherRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WeatherServiceTest {
    @Mock
    private RestTemplate restTemplate;
    @Mock
    private WeatherRepository weatherRepository;
    @InjectMocks
    private WeatherService weatherService;

    private static final String IP_ADDRESS = "124.48.0.1";
    private static final Integer LOCATION_ID = 1;
    private static final double LATITUDE = 37.536;
    private static final double LONGITUDE = 126.971;
    private static final String WEATHER_RESPONSE = "response";

    @Test
    public void shouldReturnMockedObject() {
        Location location = new Location();
        location.setIpAddress(IP_ADDRESS);
        location.setId(LOCATION_ID);
        location.setLatitude(new BigDecimal(LATITUDE).setScale(3, RoundingMode.HALF_DOWN));
        location.setLongitude(new BigDecimal(LONGITUDE).setScale(3, RoundingMode.HALF_DOWN));
        String url = "https://api.open-meteo.com/v1/forecast?latitude=" + LATITUDE
                +"&longitude="+ LONGITUDE
                +"&current_weather=true&hourly=temperature_2m";
        Weather expected = new Weather(WEATHER_RESPONSE, location, LocalDateTime.now());
        when(restTemplate.getForEntity(url, String.class))
                .thenReturn(new ResponseEntity<>(WEATHER_RESPONSE, HttpStatus.OK));
        Weather weather = weatherService.find(location);
        assertEquals(expected.getDetails(),weather.getDetails());
    }
}
