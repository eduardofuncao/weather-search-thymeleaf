package br.com.fiap.weather_search.repository;

import br.com.fiap.weather_search.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
}
