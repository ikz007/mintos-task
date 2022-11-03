package org.example.weatherforecast.service;

import com.google.gson.Gson;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.example.weatherforecast.domain.Location;
import org.example.weatherforecast.exception.LocationIdentifierException;
import org.example.weatherforecast.model.GeoLocation;
import org.example.weatherforecast.model.LocationChain;
import org.example.weatherforecast.repository.LocationRepository;
import org.example.weatherforecast.util.WeatherConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class LocationProviderService implements LocationChain {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private LocationRepository locationRepository;

    @Override
    @Retry(name = WeatherConstants.LOCATION_RETRY)
    public Location findLocation(String ipAddress) {
        log.debug("Trying to identify geolocation for an IP address: {}", ipAddress);
        String url = "http://ip-api.com/json/" + ipAddress + "?fields=lat,lon,status,query";
        ResponseEntity<String> res = restTemplate.getForEntity(url, String.class);
        Gson gson = new Gson();
        GeoLocation geoLocation = gson.fromJson(res.getBody(), GeoLocation.class);
        if(geoLocation == null || !geoLocation.status.equalsIgnoreCase("success"))
            throw new LocationIdentifierException("Failed to identify geolocation from the provided IP address");
        Location location = new Location(geoLocation);
        locationRepository.save(location);
        return location;
    }

    @Override
    public Integer getPriority() {
        return 2;
    }
}
