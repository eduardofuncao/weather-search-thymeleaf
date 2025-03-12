package br.com.fiap.weather_search.repository;

import br.com.fiap.weather_search.model.City;
import br.com.fiap.weather_search.model.WeatherInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WeatherInfoRepository extends JpaRepository<WeatherInfo, Long> {
    public List<WeatherInfo> findByCityOrderBySearchTimeDesc(City city);
}
