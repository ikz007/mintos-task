package org.example.weatherforecast.service;

import lombok.extern.slf4j.Slf4j;
import org.example.weatherforecast.domain.Location;
import org.example.weatherforecast.model.LocationChain;
import org.example.weatherforecast.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LocationService implements LocationChain {

    @Autowired
    private LocationRepository locationRepository;

    @Override
    public Location findLocation(String ipAddress) {
        return locationRepository.findByIpAddress(ipAddress);
    }

    @Override
    public Integer getPriority() {
        return 1;
    }
}
