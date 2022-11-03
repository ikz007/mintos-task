package org.example.weatherforecast.repository;

import org.example.weatherforecast.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Integer> {
    Location findByIpAddress(String ipAdress);
}
