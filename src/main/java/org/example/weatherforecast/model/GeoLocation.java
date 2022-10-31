package org.example.weatherforecast.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class GeoLocation {
    public String query;
    public String status;
    public BigDecimal lat;
    public BigDecimal lon;
}
