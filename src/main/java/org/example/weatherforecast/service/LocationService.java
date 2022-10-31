package org.example.weatherforecast.service;

import lombok.extern.slf4j.Slf4j;
import org.example.weatherforecast.domain.Location;
import org.example.weatherforecast.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Component
@Slf4j
public class LocationService implements OptionalPipeline<Optional<Location>, String>{

    private static final String LOCATION_CACHE_NAME = "location";

    @Autowired
    private LocationRepository locationRepository;

    @Cacheable(cacheNames = LOCATION_CACHE_NAME)
    public Optional<Location> find(String ipAddress) {
        return locationRepository.findByIpAddress(ipAddress);
    }
}
