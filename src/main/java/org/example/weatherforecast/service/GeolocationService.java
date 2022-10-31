package org.example.weatherforecast.service;

import lombok.extern.slf4j.Slf4j;
import org.example.weatherforecast.domain.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
@Slf4j
public class GeolocationService {

    @Autowired
    private LocationService locationService;
    @Autowired
    private IpAddressService ipAddressService;
    @Autowired
    private LocationProviderService locationProviderService;
    @Autowired
    private WeatherService weatherService;

    public Optional<Weather> process(HttpServletRequest request) {
        return Optional.of(request)
                .map(ipAddressService::find)
                .map(ip ->
                        locationService.find(ip)
                                .orElse(locationProviderService.find(ip))
                )
                .map(weatherService::find);
    }
}
