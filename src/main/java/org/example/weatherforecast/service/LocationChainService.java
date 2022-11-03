package org.example.weatherforecast.service;

import lombok.extern.slf4j.Slf4j;
import org.example.weatherforecast.domain.Location;
import org.example.weatherforecast.model.LocationChain;
import org.example.weatherforecast.util.WeatherConstants;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Comparator;
import java.util.List;

@Component
@Slf4j
public class LocationChainService implements OptionalPipeline<Location, String> {
    private final List<LocationChain> locationChainList;

    public LocationChainService(List<LocationChain> locationChainList) {
        this.locationChainList = locationChainList;
    }

    @PostConstruct
    private void init() {
        locationChainList.sort(Comparator.comparingInt(LocationChain::getPriority));
    }


    @Override
    @Cacheable(cacheNames = WeatherConstants.LOCATION_CACHE_NAME)
    public Location find(String ipAddress) {
        for(LocationChain locationChain: locationChainList) {
            Location location = locationChain.findLocation(ipAddress);
            if(location==null)
                continue;
            return location;
        }
        return null;
    }
}
