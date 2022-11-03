package org.example.weatherforecast.service;

import org.example.weatherforecast.domain.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class LocationChainServiceTest {

    private LocationChainService locationChainService;
    @Spy
    @InjectMocks
    LocationProviderService locationProviderService;

    private static final String IP_ADDRESS = "127.0.0.1";
    private final Location location = new Location();

    @BeforeEach
    public void init() {
        location.setIpAddress(IP_ADDRESS);
        locationChainService = new LocationChainService(List.of(locationProviderService));
        doReturn(location).when(locationProviderService).findLocation(IP_ADDRESS);
    }

    @Test
    public void shouldReturnLocation() {
        Location loc = locationChainService.find(IP_ADDRESS);
        assertEquals(location, loc);
    }
}
