package org.example.weatherforecast.repository;

import org.example.weatherforecast.domain.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface WeatherRepository extends JpaRepository<Weather, Integer> {
}
