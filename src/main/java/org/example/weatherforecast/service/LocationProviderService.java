package org.example.weatherforecast.service;

import com.google.gson.Gson;
import org.example.weatherforecast.domain.Location;
import org.example.weatherforecast.model.GeoLocation;
import org.example.weatherforecast.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class LocationProviderService implements OptionalPipeline<Location, String> {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private LocationRepository locationRepository;

    @Override
    @Cacheable(cacheNames = "location")
    public Location find(String ipAddress) {
        String url = "http://ip-api.com/json/" + ipAddress + "?fields=lat,lon,status,query";
        ResponseEntity<String> res = restTemplate.getForEntity(url, String.class);
        Gson gson = new Gson();
        GeoLocation geoLocation = gson.fromJson(res.getBody(), GeoLocation.class);
        Location location = new Location(geoLocation);
        locationRepository.save(location);
        return location;
    }
}
