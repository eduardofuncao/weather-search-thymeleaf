package br.com.fiap.weather_search.controller;

import br.com.fiap.weather_search.controller.DTO.CityDTO;
import br.com.fiap.weather_search.service.CityService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/cidades")
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    public String listCities(Model model) {
        model.addAttribute("cities", cityService.findAll());
        return "admin/cidade/lista";
    }

    @GetMapping("/form")
    public String novaCidadeForm(Model model) {
        model.addAttribute("cityDTO", new CityDTO());
        return "admin/cidade/form";
    }

    @PostMapping
    public String salvarCidade(@Valid @ModelAttribute("cityDTO") CityDTO cidadeDto,
                               BindingResult result,
                               RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "admin/cidade/form";
        }

        cityService.save(cidadeDto);
        redirectAttributes.addFlashAttribute("message", "Cidade salva com sucesso");
        return "redirect:/admin/cidades";
    }
}
