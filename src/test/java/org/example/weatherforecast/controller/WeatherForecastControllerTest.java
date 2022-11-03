package org.example.weatherforecast.controller;

import org.example.weatherforecast.domain.Weather;
import org.example.weatherforecast.exception.FailedToRetrieveWeatherException;
import org.example.weatherforecast.exception.LocationIdentifierException;
import org.example.weatherforecast.model.WeatherStatus;
import org.example.weatherforecast.service.GeolocationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(WeatherForecastController.class)
public class WeatherForecastControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    GeolocationService geolocationService;

    @Test
    public void shouldReturnFailedToRetrieveWeatherException() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        when(geolocationService.process(request))
                .thenThrow(new FailedToRetrieveWeatherException());
        mvc.perform(get("/weather"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturnSuccess() throws Exception {
        when(geolocationService.process(any(MockHttpServletRequest.class)))
                .thenReturn(java.util.Optional.of(new Weather(LocalDateTime.now(), WeatherStatus.Success, "Message")));
        mvc.perform(get("/weather"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnLocationIdentifierException() throws Exception {
        when(geolocationService.process(any(MockHttpServletRequest.class)))
                .thenThrow(new LocationIdentifierException());
        mvc.perform(get("/weather"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest());
    }
}
