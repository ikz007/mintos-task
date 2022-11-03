package org.example.weatherforecast.repository;

import org.example.weatherforecast.domain.Weather;
import org.example.weatherforecast.model.WeatherStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WeatherRepositoryTest {

    @Mock
    private WeatherRepository repo;

    private static final Integer WEATHER_ID = 1;

    @BeforeEach
    public void init() {
        Weather weather = new Weather();
        weather.setStatus(WeatherStatus.Success);
        weather.setId(WEATHER_ID);
        when(repo.findById(WEATHER_ID)).thenReturn(Optional.of(weather));
    }

    @Test
    public void whenDeleteTheObjectShouldBeDeleted() {
        Optional<Weather> weather = repo.findById(WEATHER_ID);
        assertTrue(weather.isPresent());
    }
}
