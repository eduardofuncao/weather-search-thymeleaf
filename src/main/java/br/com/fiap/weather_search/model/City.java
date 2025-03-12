package br.com.fiap.weather_search.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private Double latitude;
    private Double longitude;

    @OneToMany(mappedBy = "city")
    private List<WeatherInfo> weatherInfoList;
}
