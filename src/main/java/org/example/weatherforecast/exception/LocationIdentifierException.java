package org.example.weatherforecast.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocationIdentifierException extends RuntimeException {
    public LocationIdentifierException() {
        super();
    }

    public LocationIdentifierException(String message) {
        super(message);
    }
}
