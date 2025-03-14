package br.com.fiap.weather_search.controller.DTO;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.aspectj.lang.annotation.DeclareMixin;

@Data
public class CityDTO {
    private long id;
    @NotNull(message = "City name is mandatory")
    private String name;

    @NotNull(message = "Latitude value is mandatory")
    @DecimalMin(value = "-90.0", message = "Latitude must greater than -90")
    @DecimalMax(value = "90.0", message = "Latitude must be less than 90")
    private Double latitude;

    @NotNull(message = "Longitude value is mandatory")
    @DecimalMin(value = "-180.0", message = "Longitude must be greater than -180")
    @DecimalMax(value = "180.0", message = "Longitude must be less than 180")
    private Double longitude;
}
