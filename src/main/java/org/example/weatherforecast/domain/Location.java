package org.example.weatherforecast.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.weatherforecast.model.GeoLocation;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity(name = "GEOLOCATION")
@Table(name = "GEOLOCATION")
@Data
@NoArgsConstructor
public class Location implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ip_address")
    private String ipAddress;

    @Column(name = "latitude")
    private BigDecimal latitude;

    @Column(name = "longitude")
    private BigDecimal longitude;

    public Location(GeoLocation geoLocation) {
        this.ipAddress = geoLocation.query;
        this.latitude = geoLocation.lat;
        this.longitude = geoLocation.lon;
    }
}
