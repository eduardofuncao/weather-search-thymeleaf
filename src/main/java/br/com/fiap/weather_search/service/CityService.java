package br.com.fiap.weather_search.service;

import br.com.fiap.weather_search.controller.DTO.CityDTO;
import br.com.fiap.weather_search.model.City;
import br.com.fiap.weather_search.repository.CityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<CityDTO> findAll() {
        return cityRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public CityDTO save(CityDTO cityDTO) {
        City savedCity = cityRepository.save(toEntity(cityDTO));
        return toDTO(savedCity);
    }


    private CityDTO toDTO(City city) {
        CityDTO dto = new CityDTO();
        dto.setId(city.getId());
        dto.setName(city.getName());
        dto.setLatitude(city.getLatitude());
        dto.setLongitude(city.getLongitude());
        return dto;
    }

    private City toEntity(CityDTO dto) {
        City city = new City();
        city.setId(dto.getId());
        city.setName(dto.getName());
        city.setLatitude(dto.getLatitude());
        city.setLongitude(dto.getLongitude());
        return city;
    }

}
