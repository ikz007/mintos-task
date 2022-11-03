package org.example.weatherforecast.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeoLocation {
    public String query;
    public String status;
    public BigDecimal lat;
    public BigDecimal lon;
}
