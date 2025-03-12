package br.com.fiap.weather_search.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class WeatherInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Double temperature;
    private Double windSpeed;
    private String weatherConditions;
    private LocalDateTime searchTime;

    @ManyToOne
    private City city;

}
