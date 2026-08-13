package com.tournament.controller;

import com.tournament.model.Utakmica;
import com.tournament.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/utakmice")
public class UtakmicaController {

    private final UtakmicaService utakmicaService;
    private final TimService timService;
    private final TerenService terenService;
    private final SudijaService sudijaService;

    public UtakmicaController(UtakmicaService utakmicaService, TimService timService,
                               TerenService terenService, SudijaService sudijaService) {
        this.utakmicaService = utakmicaService;
        this.timService = timService;
        this.terenService = terenService;
        this.sudijaService = sudijaService;
    }

    @GetMapping
    public String lista(Model model) {
        model.addAttribute("utakmice", utakmicaService.sveUtakmice());
        model.addAttribute("timovi", timService.sviTimovi());
        model.addAttribute("tereni", terenService.sviTereni());
        return "utakmica/lista";
    }

    @GetMapping("/{id}")
    public String detalji(@PathVariable Long id, Model model) {
        Utakmica utakmica = utakmicaService.nadjiPoId(id);
        if (utakmica == null) return "redirect:/utakmice";
        model.addAttribute("utakmica", utakmica);
        model.addAttribute("domacin", timService.nadjiPoId(utakmica.getDomacinId()));
        model.addAttribute("gost", timService.nadjiPoId(utakmica.getGostId()));
        model.addAttribute("teren", terenService.nadjiPoId(utakmica.getTerenId()));
        model.addAttribute("sudija", sudijaService.nadjiPoId(utakmica.getSudijaId()));
        return "utakmica/detalji";
    }

    @GetMapping("/novi")
    public String formaZaDodavanje(Model model) {
        model.addAttribute("utakmica", new Utakmica());
        model.addAttribute("timovi", timService.sviTimovi());
        model.addAttribute("tereni", terenService.sviTereni());
        model.addAttribute("sudije", sudijaService.sveSudije());
        model.addAttribute("naslov", "Zakaži novu utakmicu");
        return "utakmica/forma";
    }

    @GetMapping("/izmeni/{id}")
    public String formaZaIzmenu(@PathVariable Long id, Model model) {
        Utakmica utakmica = utakmicaService.nadjiPoId(id);
        if (utakmica == null) return "redirect:/utakmice";
        model.addAttribute("utakmica", utakmica);
        model.addAttribute("timovi", timService.sviTimovi());
        model.addAttribute("tereni", terenService.sviTereni());
        model.addAttribute("sudije", sudijaService.sveSudije());
        model.addAttribute("naslov", "Izmeni utakmicu");
        return "utakmica/forma";
    }

    @PostMapping("/sacuvaj")
    public String sacuvaj(@ModelAttribute Utakmica utakmica) {
        utakmicaService.sacuvaj(utakmica);
        return "redirect:/utakmice";
    }

    @GetMapping("/obrisi/{id}")
    public String obrisi(@PathVariable Long id) {
        utakmicaService.obrisi(id);
        return "redirect:/utakmice";
    }
}
