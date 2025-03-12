package br.com.fiap.weather_search.client;

import br.com.fiap.weather_search.model.City;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class OpenMeteoClient {

    private final RestClient restClient;

    public OpenMeteoClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public OpenMeteoResponse fetchWeatherInfo(City city) {
        String uri = "/forecast?latitude=" + city.getLatitude() + "&longitude=" + city.getLongitude()+ "&current_weather=true";
        return restClient.get()
                .uri(uri)
                .header("Accept", "application/json")
                .retrieve()
                .body(OpenMeteoResponse.class);

    }

}
