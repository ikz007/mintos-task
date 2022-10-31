package org.example.weatherforecast.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity(name = "WEATHER")
@Table(name = "WEATHER")
@Data
@NoArgsConstructor
public class Weather implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "json", name = "details")
    private String details;

    @Column(name = "request_time")
    private LocalDate requestTime;

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;
}
