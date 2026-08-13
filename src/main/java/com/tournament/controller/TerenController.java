package com.tournament.controller;

import com.tournament.model.Teren;
import com.tournament.service.TerenService;
import com.tournament.service.UtakmicaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tereni")
public class TerenController {

    private final TerenService terenService;
    private final UtakmicaService utakmicaService;

    public TerenController(TerenService terenService, UtakmicaService utakmicaService) {
        this.terenService = terenService;
        this.utakmicaService = utakmicaService;
    }

    @GetMapping
    public String lista(Model model) {
        model.addAttribute("tereni", terenService.sviTereni());
        return "teren/lista";
    }

    @GetMapping("/{id}")
    public String detalji(@PathVariable Long id, Model model) {
        Teren teren = terenService.nadjiPoId(id);
        if (teren == null) return "redirect:/tereni";
        model.addAttribute("teren", teren);
        model.addAttribute("utakmice", utakmicaService.utakmiceNaTerenu(id));
        return "teren/detalji";
    }

    @GetMapping("/novi")
    public String formaZaDodavanje(Model model) {
        model.addAttribute("teren", new Teren());
        model.addAttribute("naslov", "Dodaj novi teren");
        return "teren/forma";
    }

    @GetMapping("/izmeni/{id}")
    public String formaZaIzmenu(@PathVariable Long id, Model model) {
        Teren teren = terenService.nadjiPoId(id);
        if (teren == null) return "redirect:/tereni";
        model.addAttribute("teren", teren);
        model.addAttribute("naslov", "Izmeni teren");
        return "teren/forma";
    }

    @PostMapping("/sacuvaj")
    public String sacuvaj(@ModelAttribute Teren teren) {
        terenService.sacuvaj(teren);
        return "redirect:/tereni";
    }

    @GetMapping("/obrisi/{id}")
    public String obrisi(@PathVariable Long id) {
        terenService.obrisi(id);
        return "redirect:/tereni";
    }
}
