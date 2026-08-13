package com.tournament.service;

import com.tournament.data.ApplicationData;
import com.tournament.model.Utakmica;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UtakmicaService {

    private final ApplicationData data;

    public UtakmicaService(ApplicationData data) {
        this.data = data;
    }

    public List<Utakmica> sveUtakmice() {
        return new ArrayList<>(data.getUtakmice().values());
    }

    public List<Utakmica> utakmiceNaTerenu(Long terenId) {
        return data.getUtakmice().values().stream()
                .filter(u -> terenId.equals(u.getTerenId()))
                .collect(Collectors.toList());
    }

    public Utakmica nadjiPoId(Long id) {
        return data.getUtakmice().get(id);
    }

    public void sacuvaj(Utakmica utakmica) {
        if (utakmica.getId() == null) {
            utakmica.setId(data.nextUtakmicaId());
        }
        data.getUtakmice().put(utakmica.getId(), utakmica);
    }

    public void obrisi(Long id) {
        data.getUtakmice().remove(id);
    }
}
