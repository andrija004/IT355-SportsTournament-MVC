package com.tournament.service;

import com.tournament.data.ApplicationData;
import com.tournament.model.Igrac;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IgracService {

    private final ApplicationData data;

    public IgracService(ApplicationData data) {
        this.data = data;
    }

    public List<Igrac> sviIgraci() {
        return new ArrayList<>(data.getIgraci().values());
    }

    public List<Igrac> igraciBriga(Long timId) {
        return data.getIgraci().values().stream()
                .filter(i -> timId.equals(i.getTimId()))
                .collect(Collectors.toList());
    }

    public Igrac nadjiPoId(Long id) {
        return data.getIgraci().get(id);
    }

    public void sacuvaj(Igrac igrac) {
        if (igrac.getId() == null) {
            igrac.setId(data.nextIgracId());
        }
        data.getIgraci().put(igrac.getId(), igrac);
    }

    public void obrisi(Long id) {
        data.getIgraci().remove(id);
    }
}
