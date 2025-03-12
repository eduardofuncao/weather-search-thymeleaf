package br.com.fiap.weather_search.controller.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CityDTO {
    private long id;
    @NotNull(message = "City name is mandatory")
    private String name;
    @NotNull(message = "Latitude value is mandatory")
    private Double latitude;
    @NotNull(message = "Longitude value is mandatory")
    private Double longitude;
}
