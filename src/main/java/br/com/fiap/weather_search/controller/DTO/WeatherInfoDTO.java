package br.com.fiap.weather_search.controller.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WeatherInfoDTO {

    private long id;
    private Double temperature;
    private Double windSpeed;
    private String weatherConditions;
    private LocalDateTime searchTime;
    private long cityId;
}
