package br.com.fiap.weather_search.service;

import br.com.fiap.weather_search.client.OpenMeteoClient;
import br.com.fiap.weather_search.client.OpenMeteoResponse;
import br.com.fiap.weather_search.controller.DTO.WeatherInfoDTO;
import br.com.fiap.weather_search.model.City;
import br.com.fiap.weather_search.model.WeatherInfo;
import br.com.fiap.weather_search.repository.CityRepository;
import br.com.fiap.weather_search.repository.WeatherInfoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeatherInfoService {

    private final OpenMeteoClient openMeteoClient;
    private final CityRepository cityRepository;
    private final WeatherInfoRepository weatherInfoRepository;

    public WeatherInfoService(OpenMeteoClient openMeteoClient, CityRepository cityRepository, WeatherInfoRepository weatherInfoRepository) {
        this.openMeteoClient = openMeteoClient;
        this.cityRepository = cityRepository;
        this.weatherInfoRepository = weatherInfoRepository;
    }

    public WeatherInfoDTO fetchByCityId(Long cityId) {
        City city = cityRepository.findById(cityId)
                .orElseThrow(() -> new RuntimeException("City with id " + cityId + " not found"));

        OpenMeteoResponse response = openMeteoClient.fetchWeatherInfo(city);
        System.out.println(response);
        WeatherInfo weatherInfo = new WeatherInfo();
        weatherInfo.setCity(city);
        weatherInfo.setTemperature(response.getCurrentWeather().getTemperature());
        weatherInfo.setWindSpeed(response.getCurrentWeather().getWindspeed());
        weatherInfo.setWeatherConditions(convertWeatherConditionCodeToDescription(response.getCurrentWeather().getWeathercode()));
        weatherInfo.setSearchTime(response.getCurrentWeather().getTime());

        weatherInfo = weatherInfoRepository.save(weatherInfo);
        return toDTO(weatherInfo);
    }

    // para testes, retorna todas as consultas de clima feitas para uma cidade
    public List<WeatherInfoDTO> findAllByCityId(Long cityId) {
        City city = cityRepository.findById(cityId)
                .orElseThrow(() -> new RuntimeException("City with id " + cityId + " not found"));

        return weatherInfoRepository.findByCityOrderBySearchTimeDesc(city)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    private String convertWeatherConditionCodeToDescription(int weatherConditionCode) {
        if (weatherConditionCode <= 1) return "Clear";
        if (weatherConditionCode <= 3) return "Cloudy";
        if (weatherConditionCode <= 48) return "Foggy";
        if (weatherConditionCode <= 69) return "Raining";
        if (weatherConditionCode <= 79) return "Snowing";
        if (weatherConditionCode <= 99) return "Thunderstorm";
        return "Unknown";
    }

    private WeatherInfoDTO toDTO(WeatherInfo weatherInfo) {
        WeatherInfoDTO dto = new WeatherInfoDTO();
        dto.setId(weatherInfo.getId());
        dto.setTemperature(weatherInfo.getTemperature());
        dto.setWindSpeed(weatherInfo.getWindSpeed());
        dto.setWeatherConditions(weatherInfo.getWeatherConditions());
        dto.setSearchTime(weatherInfo.getSearchTime());
        dto.setCityId(weatherInfo.getCity().getId());
        return dto;
    }

}
