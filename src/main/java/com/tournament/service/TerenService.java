package com.tournament.service;

import com.tournament.data.ApplicationData;
import com.tournament.model.Teren;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TerenService {

    private final ApplicationData data;

    public TerenService(ApplicationData data) {
        this.data = data;
    }

    public List<Teren> sviTereni() {
        return new ArrayList<>(data.getTereni().values());
    }

    public Teren nadjiPoId(Long id) {
        return data.getTereni().get(id);
    }

    public void sacuvaj(Teren teren) {
        if (teren.getId() == null) {
            teren.setId(data.nextTerenId());
        }
        data.getTereni().put(teren.getId(), teren);
    }

    public void obrisi(Long id) {
        data.getTereni().remove(id);
    }
}
