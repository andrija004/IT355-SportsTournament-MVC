package com.tournament.service;

import com.tournament.data.ApplicationData;
import com.tournament.model.Tim;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TimService {

    private final ApplicationData data;

    public TimService(ApplicationData data) {
        this.data = data;
    }

    public List<Tim> sviTimovi() {
        return new ArrayList<>(data.getTimovi().values());
    }

    public Tim nadjiPoId(Long id) {
        return data.getTimovi().get(id);
    }

    public void sacuvaj(Tim tim) {
        if (tim.getId() == null) {
            tim.setId(data.nextTimId());
        }
        data.getTimovi().put(tim.getId(), tim);
    }

    public void obrisi(Long id) {
        data.getTimovi().remove(id);
    }
}
