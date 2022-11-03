package org.example.weatherforecast.model;

import org.example.weatherforecast.domain.Location;

public interface LocationChain {
    Location findLocation(String ipAddress);
    Integer getPriority();
}
