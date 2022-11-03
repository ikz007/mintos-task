package org.example.weatherforecast.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.weatherforecast.domain.Weather;
import org.example.weatherforecast.exception.FailedToRetrieveWeatherException;
import org.example.weatherforecast.service.GeolocationService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@Slf4j
public class WeatherForecastController {

    private final HttpServletRequest request;
    private final GeolocationService service;

    public WeatherForecastController(HttpServletRequest request, GeolocationService service) {
        this.request = request;
        this.service = service;
    }

    @CrossOrigin
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/weather",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Weather getWeather(HttpServletRequest request) {
        Optional<Weather> weather = service.process(request);
        try {
            return weather.get();
        } catch (Exception exception) {
            throw new FailedToRetrieveWeatherException();
        }
    }
}
