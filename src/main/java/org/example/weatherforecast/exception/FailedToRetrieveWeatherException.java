package org.example.weatherforecast.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FailedToRetrieveWeatherException extends RuntimeException {
    public FailedToRetrieveWeatherException() {
        super();
    }

    public FailedToRetrieveWeatherException(String message) {
        super(message);
    }
}
