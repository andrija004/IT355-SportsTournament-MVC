package com.tournament.model;

public class Teren {

    private Long id;
    private String naziv;
    private String lokacija;
    private int kapacitet;
    private String tip;

    public Teren() {}

    public Teren(Long id, String naziv, String lokacija, int kapacitet, String tip) {
        this.id = id;
        this.naziv = naziv;
        this.lokacija = lokacija;
        this.kapacitet = kapacitet;
        this.tip = tip;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNaziv() { return naziv; }
    public void setNaziv(String naziv) { this.naziv = naziv; }

    public String getLokacija() { return lokacija; }
    public void setLokacija(String lokacija) { this.lokacija = lokacija; }

    public int getKapacitet() { return kapacitet; }
    public void setKapacitet(int kapacitet) { this.kapacitet = kapacitet; }

    public String getTip() { return tip; }
    public void setTip(String tip) { this.tip = tip; }

    @Override
    public String toString() {
        return naziv + " - " + lokacija;
    }
}
