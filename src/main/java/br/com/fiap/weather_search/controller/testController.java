package br.com.fiap.weather_search.controller;

import br.com.fiap.weather_search.controller.DTO.CityDTO;
import br.com.fiap.weather_search.controller.DTO.WeatherInfoDTO;
import br.com.fiap.weather_search.service.CityService;
import br.com.fiap.weather_search.service.WeatherInfoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Controller criado apenas para teste das funcionalidades
@RestController
@RequestMapping("api/")
public class testController {

    private final WeatherInfoService weatherInfoService;
    private final CityService cityService;

    public testController(WeatherInfoService weatherInfoService, CityService cityService) {
        this.weatherInfoService = weatherInfoService;
        this.cityService = cityService;
    }

    @GetMapping("/cities")
    public ResponseEntity<List<CityDTO>> getAllCities() {
        return ResponseEntity.ok(cityService.findAll());
    }

    @PostMapping("/cities")
    public ResponseEntity<CityDTO> saveCity(@RequestBody CityDTO cityDTO) {
        return ResponseEntity.ok(cityService.save(cityDTO));
    }

    @GetMapping("/weather/{cityId}/list")
    public ResponseEntity<List<WeatherInfoDTO>> getAllWeatherInfoByCity(@PathVariable long cityId) {
        return ResponseEntity.ok(weatherInfoService.findAllByCityId(cityId));
    }

    @GetMapping("/weather/{cityId}/now")
    public ResponseEntity<WeatherInfoDTO> fetchWeatherInfoByCity(@PathVariable long cityId) {
        return ResponseEntity.ok(weatherInfoService.fetchByCityId(cityId));
    }

}
