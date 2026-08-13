package com.tournament.controller;

import com.tournament.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final TimService timService;
    private final IgracService igracService;
    private final TerenService terenService;
    private final SudijaService sudijaService;
    private final UtakmicaService utakmicaService;

    public HomeController(TimService timService, IgracService igracService,
                          TerenService terenService, SudijaService sudijaService,
                          UtakmicaService utakmicaService) {
        this.timService = timService;
        this.igracService = igracService;
        this.terenService = terenService;
        this.sudijaService = sudijaService;
        this.utakmicaService = utakmicaService;
    }

    @GetMapping("/")
    public String pocetna(Model model) {
        model.addAttribute("brojTimova", timService.sviTimovi().size());
        model.addAttribute("brojIgraca", igracService.sviIgraci().size());
        model.addAttribute("brojTerena", terenService.sviTereni().size());
        model.addAttribute("brojSudija", sudijaService.sveSudije().size());
        model.addAttribute("brojUtakmica", utakmicaService.sveUtakmice().size());
        model.addAttribute("poslednjeUtakmice", utakmicaService.sveUtakmice());
        return "index";
    }
}
