package com.tournament.controller;

import com.tournament.model.Tim;
import com.tournament.service.IgracService;
import com.tournament.service.TimService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/timovi")
public class TimController {

    private final TimService timService;
    private final IgracService igracService;

    public TimController(TimService timService, IgracService igracService) {
        this.timService = timService;
        this.igracService = igracService;
    }

    @GetMapping
    public String lista(Model model) {
        model.addAttribute("timovi", timService.sviTimovi());
        return "tim/lista";
    }

    @GetMapping("/{id}")
    public String detalji(@PathVariable Long id, Model model) {
        Tim tim = timService.nadjiPoId(id);
        if (tim == null) return "redirect:/timovi";
        model.addAttribute("tim", tim);
        model.addAttribute("igraci", igracService.igraciBriga(id));
        return "tim/detalji";
    }

    @GetMapping("/novi")
    public String formaZaDodavanje(Model model) {
        model.addAttribute("tim", new Tim());
        model.addAttribute("naslov", "Dodaj novi tim");
        return "tim/forma";
    }

    @GetMapping("/izmeni/{id}")
    public String formaZaIzmenu(@PathVariable Long id, Model model) {
        Tim tim = timService.nadjiPoId(id);
        if (tim == null) return "redirect:/timovi";
        model.addAttribute("tim", tim);
        model.addAttribute("naslov", "Izmeni tim");
        return "tim/forma";
    }

    @PostMapping("/sacuvaj")
    public String sacuvaj(@ModelAttribute Tim tim) {
        timService.sacuvaj(tim);
        return "redirect:/timovi";
    }

    @GetMapping("/obrisi/{id}")
    public String obrisi(@PathVariable Long id) {
        timService.obrisi(id);
        return "redirect:/timovi";
    }
}
