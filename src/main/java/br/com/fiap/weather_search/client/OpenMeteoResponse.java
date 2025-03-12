package br.com.fiap.weather_search.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OpenMeteoResponse {
    @JsonProperty("current_weather")
    private CurrentWeather currentWeather;

    @Data
    public static class CurrentWeather {
        private LocalDateTime time;
        private double temperature;
        private double windspeed;
        private int weathercode;
    }
}
