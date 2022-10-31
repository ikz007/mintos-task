package org.example.weatherforecast.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.weatherforecast.domain.Weather;
import org.example.weatherforecast.service.GeolocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
public class WeatherForecastController {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private GeolocationService service;

    @CrossOrigin
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/weather",
            produces = MediaType.TEXT_PLAIN_VALUE
    )
    public Weather getWeather(HttpServletRequest request) {
        return service.process(request).orElse(null);
    }
}
