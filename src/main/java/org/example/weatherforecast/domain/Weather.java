package org.example.weatherforecast.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRawValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.weatherforecast.model.WeatherStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity(name = "WEATHER")
@Table(name = "WEATHER")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Weather implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Integer id;

    @Column(columnDefinition = "json", name = "details")
    @JsonRawValue
    private String details;

    @Column(name = "request_time")
    private LocalDateTime requestTime;

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    @JsonIgnore
    private Location location;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private WeatherStatus status;

    @Transient
    private String message;

    public Weather(LocalDateTime requestTime, WeatherStatus status, Location location) {
        this.requestTime = requestTime;
        this.location = location;
        this.status = status;
    }

    public Weather(LocalDateTime requestTime, WeatherStatus status, String message) {
        this.requestTime = requestTime;
        this.status = status;
        this.message = message;
    }

    public Weather(String details, Location location, LocalDateTime requestTime) {
        this.requestTime = requestTime;
        this.location = location;
        this.details = details;
        this.status = WeatherStatus.Success;
    }
}
