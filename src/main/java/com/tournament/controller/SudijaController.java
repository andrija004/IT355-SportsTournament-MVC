package com.tournament.controller;

import com.tournament.model.Sudija;
import com.tournament.service.SudijaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/sudije")
public class SudijaController {

    private final SudijaService sudijaService;

    public SudijaController(SudijaService sudijaService) {
        this.sudijaService = sudijaService;
    }

    @GetMapping
    public String lista(Model model) {
        model.addAttribute("sudije", sudijaService.sveSudije());
        return "sudija/lista";
    }

    @GetMapping("/{id}")
    public String detalji(@PathVariable Long id, Model model) {
        Sudija sudija = sudijaService.nadjiPoId(id);
        if (sudija == null) return "redirect:/sudije";
        model.addAttribute("sudija", sudija);
        return "sudija/detalji";
    }

    @GetMapping("/novi")
    public String formaZaDodavanje(Model model) {
        model.addAttribute("sudija", new Sudija());
        model.addAttribute("naslov", "Dodaj novog sudiju");
        return "sudija/forma";
    }

    @GetMapping("/izmeni/{id}")
    public String formaZaIzmenu(@PathVariable Long id, Model model) {
        Sudija sudija = sudijaService.nadjiPoId(id);
        if (sudija == null) return "redirect:/sudije";
        model.addAttribute("sudija", sudija);
        model.addAttribute("naslov", "Izmeni sudiju");
        return "sudija/forma";
    }

    @PostMapping("/sacuvaj")
    public String sacuvaj(@ModelAttribute Sudija sudija) {
        sudijaService.sacuvaj(sudija);
        return "redirect:/sudije";
    }

    @GetMapping("/obrisi/{id}")
    public String obrisi(@PathVariable Long id) {
        sudijaService.obrisi(id);
        return "redirect:/sudije";
    }
}
