package com.tournament.controller;

import com.tournament.model.Igrac;
import com.tournament.service.IgracService;
import com.tournament.service.TimService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/igraci")
public class IgracController {

    private final IgracService igracService;
    private final TimService timService;

    public IgracController(IgracService igracService, TimService timService) {
        this.igracService = igracService;
        this.timService = timService;
    }

    @GetMapping
    public String lista(Model model) {
        model.addAttribute("igraci", igracService.sviIgraci());
        model.addAttribute("timovi", timService.sviTimovi());
        return "igrac/lista";
    }

    @GetMapping("/{id}")
    public String detalji(@PathVariable Long id, Model model) {
        Igrac igrac = igracService.nadjiPoId(id);
        if (igrac == null) return "redirect:/igraci";
        model.addAttribute("igrac", igrac);
        model.addAttribute("tim", timService.nadjiPoId(igrac.getTimId()));
        return "igrac/detalji";
    }

    @GetMapping("/novi")
    public String formaZaDodavanje(Model model) {
        model.addAttribute("igrac", new Igrac());
        model.addAttribute("timovi", timService.sviTimovi());
        model.addAttribute("naslov", "Dodaj novog igrača");
        return "igrac/forma";
    }

    @GetMapping("/izmeni/{id}")
    public String formaZaIzmenu(@PathVariable Long id, Model model) {
        Igrac igrac = igracService.nadjiPoId(id);
        if (igrac == null) return "redirect:/igraci";
        model.addAttribute("igrac", igrac);
        model.addAttribute("timovi", timService.sviTimovi());
        model.addAttribute("naslov", "Izmeni igrača");
        return "igrac/forma";
    }

    @PostMapping("/sacuvaj")
    public String sacuvaj(@ModelAttribute Igrac igrac) {
        igracService.sacuvaj(igrac);
        return "redirect:/igraci";
    }

    @GetMapping("/obrisi/{id}")
    public String obrisi(@PathVariable Long id) {
        igracService.obrisi(id);
        return "redirect:/igraci";
    }
}
