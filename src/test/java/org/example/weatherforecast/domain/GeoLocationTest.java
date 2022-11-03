package org.example.weatherforecast.domain;

import com.google.gson.Gson;
import org.example.weatherforecast.model.GeoLocation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class GeoLocationTest {
    @Test
    public void shouldParseTextToGeoLocation() {
        String json = "{ \"query\": \"124.48.0.1\", \"status\": \"success\", \"lat\": 37.536, \"lon\": 126.971 }";
        BigDecimal lat = new BigDecimal("37.536").setScale(3, RoundingMode.HALF_DOWN);
        BigDecimal lon = new BigDecimal("126.971").setScale(3, RoundingMode.HALF_DOWN);
        GeoLocation test = new GeoLocation("124.48.0.1", "success", lat, lon);
        Gson gson = new Gson();
        GeoLocation geoLocation = gson.fromJson(json, GeoLocation.class);
        Assertions.assertEquals(test, geoLocation);
    }
}
