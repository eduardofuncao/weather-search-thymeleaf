package br.com.fiap.weather_search.controller;

import br.com.fiap.weather_search.controller.DTO.WeatherInfoDTO;
import br.com.fiap.weather_search.service.CityService;
import br.com.fiap.weather_search.service.WeatherInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/clima")
public class WeatherInfoController {

    private final WeatherInfoService weatherInfoService;
    private final CityService cityService;

    public WeatherInfoController(WeatherInfoService weatherInfoService, CityService cityService) {
        this.weatherInfoService = weatherInfoService;
        this.cityService = cityService;
    }

    @GetMapping("/consulta")
    public String consultaForm(Model model) {
        model.addAttribute("cities", cityService.findAll());
        return "clima/consulta";
    }

    @PostMapping("/consultar")
    public String consultarClima(@RequestParam("cidadeId") Long cidadeId,
                                 RedirectAttributes redirectAttributes) {
        try {
            // No need to fetch weather info here since we're redirecting
            return "redirect:/clima/resultado/" + cidadeId;
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Erro ao consultar clima: " + e.getMessage());
            return "redirect:/clima/consulta";
        }
    }

    @GetMapping("/resultado/{cidadeId}")
    public String mostrarResultado(@PathVariable("cidadeId") Long cidadeId, Model model) {
        try {
            WeatherInfoDTO weatherInfoDTO = weatherInfoService.fetchByCityId(cidadeId);
            model.addAttribute("city", cityService.findById(cidadeId));
            model.addAttribute("weatherInfo", weatherInfoDTO);
            model.addAttribute("cities", cityService.findAll());
            return "clima/resultado";
        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao consultar clima: " + e.getMessage());
            model.addAttribute("cities", cityService.findAll());
            return "clima/consulta";
        }
    }

    @GetMapping("/historico/{cityId}")
    public String historicoClima(@PathVariable("cityId") Long cityId, Model model) {
        model.addAttribute("city", cityService.findById(cityId));
        model.addAttribute("records", weatherInfoService.findAllByCityId(cityId));
        return "clima/historico";
    }
}
