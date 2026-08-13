package com.tournament.model;

import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

public class Utakmica {

    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime datumVreme;

    private Long domacinId;
    private Long gostId;
    private Long terenId;
    private Long sudijaId;
    private String rezultat;

    public Utakmica() {}

    public Utakmica(Long id, LocalDateTime datumVreme, Long domacinId, Long gostId,
                    Long terenId, Long sudijaId, String rezultat) {
        this.id = id;
        this.datumVreme = datumVreme;
        this.domacinId = domacinId;
        this.gostId = gostId;
        this.terenId = terenId;
        this.sudijaId = sudijaId;
        this.rezultat = rezultat;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getDatumVreme() { return datumVreme; }
    public void setDatumVreme(LocalDateTime datumVreme) { this.datumVreme = datumVreme; }

    public Long getDomacinId() { return domacinId; }
    public void setDomacinId(Long domacinId) { this.domacinId = domacinId; }

    public Long getGostId() { return gostId; }
    public void setGostId(Long gostId) { this.gostId = gostId; }

    public Long getTerenId() { return terenId; }
    public void setTerenId(Long terenId) { this.terenId = terenId; }

    public Long getSudijaId() { return sudijaId; }
    public void setSudijaId(Long sudijaId) { this.sudijaId = sudijaId; }

    public String getRezultat() { return rezultat; }
    public void setRezultat(String rezultat) { this.rezultat = rezultat; }
}
