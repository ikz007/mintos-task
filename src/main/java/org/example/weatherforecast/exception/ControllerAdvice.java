package org.example.weatherforecast.exception;

import org.example.weatherforecast.domain.Weather;
import org.example.weatherforecast.model.WeatherStatus;
import org.example.weatherforecast.util.WeatherConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(LocationIdentifierException.class)
    public ResponseEntity<Weather> handleLocationNotIndentifiedException(LocationIdentifierException ex) {
        Weather weather = new Weather(LocalDateTime.now(), WeatherStatus.Fail, WeatherConstants.FAILED_TO_IDENTIFY_LOCATION);
        return new ResponseEntity<>(weather, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FailedToRetrieveWeatherException.class)
    public ResponseEntity<Weather> handleFailedToRetrieveWeatherException(FailedToRetrieveWeatherException ex) {
        Weather weather = new Weather(LocalDateTime.now(), WeatherStatus.Fail, WeatherConstants.FAILED_TO_IDENTIFY_WEATHER);
        return new ResponseEntity<>(weather, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Weather> handleUnExpectedException(Exception exception) {
        Weather weather = new Weather(LocalDateTime.now(), WeatherStatus.Fail, WeatherConstants.SOMETHING_WENT_WRONG);
        return new ResponseEntity<>(weather, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
