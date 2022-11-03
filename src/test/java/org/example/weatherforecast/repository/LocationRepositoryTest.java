package org.example.weatherforecast.repository;

import org.example.weatherforecast.domain.Location;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LocationRepositoryTest {

    @Mock
    private LocationRepository repo;

    private static final String IP_ADDRESS = "127.0.0.1";
    private static final Integer LOCATION_ID = 1;

    @Test
    public void shouldReturnCorrectIdAndIpAddress() {
        Location location = new Location();
        location.setIpAddress(IP_ADDRESS);
        location.setId(LOCATION_ID);

        when(repo.findByIpAddress(IP_ADDRESS)).thenReturn(location);
        when(repo.findById(LOCATION_ID)).thenReturn(Optional.of(location));

        Location res = repo.findByIpAddress(IP_ADDRESS);
        assertEquals(LOCATION_ID, res.getId());

        Optional<Location> res2 = repo.findById(LOCATION_ID);
        assertTrue(res2.isPresent());
    }
}
