package com.tournament.model;

public class Tim {

    private Long id;
    private String naziv;
    private String grad;
    private int godinaOsnivanja;
    private String trener;

    public Tim() {}

    public Tim(Long id, String naziv, String grad, int godinaOsnivanja, String trener) {
        this.id = id;
        this.naziv = naziv;
        this.grad = grad;
        this.godinaOsnivanja = godinaOsnivanja;
        this.trener = trener;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNaziv() { return naziv; }
    public void setNaziv(String naziv) { this.naziv = naziv; }

    public String getGrad() { return grad; }
    public void setGrad(String grad) { this.grad = grad; }

    public int getGodinaOsnivanja() { return godinaOsnivanja; }
    public void setGodinaOsnivanja(int godinaOsnivanja) { this.godinaOsnivanja = godinaOsnivanja; }

    public String getTrener() { return trener; }
    public void setTrener(String trener) { this.trener = trener; }

    @Override
    public String toString() {
        return naziv + " (" + grad + ")";
    }
}
