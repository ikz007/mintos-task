package org.example.weatherforecast.repository;

import org.example.weatherforecast.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, Integer> {
    Optional<Location> findByIpAddress(String ipAdress);
}
