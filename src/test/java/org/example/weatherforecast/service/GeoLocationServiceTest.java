//package org.example.weatherforecast.service;
//
//import org.example.weatherforecast.Application;
//import org.example.weatherforecast.domain.Weather;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.mock.web.MockHttpServletRequest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.util.Optional;
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest(classes = {Application.class})
//public class GeoLocationServiceTest {
//
//    @Autowired
//    private GeolocationService geolocationService;
//
//    @Test
//    void shouldReturnWeather() {
//        MockHttpServletRequest request = new MockHttpServletRequest();
//        request.addHeader("X-Forwarded-For", "124.48.0.1");
//        Optional<Weather> weather = geolocationService.process(request);
//        Assertions.assertTrue(weather.isPresent());
//    }
//}
