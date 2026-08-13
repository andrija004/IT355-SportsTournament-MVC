package com.tournament.service;

import com.tournament.data.ApplicationData;
import com.tournament.model.Sudija;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SudijaService {

    private final ApplicationData data;

    public SudijaService(ApplicationData data) {
        this.data = data;
    }

    public List<Sudija> sveSudije() {
        return new ArrayList<>(data.getSudije().values());
    }

    public Sudija nadjiPoId(Long id) {
        return data.getSudije().get(id);
    }

    public void sacuvaj(Sudija sudija) {
        if (sudija.getId() == null) {
            sudija.setId(data.nextSudijaId());
        }
        data.getSudije().put(sudija.getId(), sudija);
    }

    public void obrisi(Long id) {
        data.getSudije().remove(id);
    }
}
