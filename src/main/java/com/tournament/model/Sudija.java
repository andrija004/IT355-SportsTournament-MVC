package com.tournament.model;

public class Sudija {

    private Long id;
    private String ime;
    private String prezime;
    private String licenca;
    private int godineIskustva;

    public Sudija() {}

    public Sudija(Long id, String ime, String prezime, String licenca, int godineIskustva) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.licenca = licenca;
        this.godineIskustva = godineIskustva;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getIme() { return ime; }
    public void setIme(String ime) { this.ime = ime; }

    public String getPrezime() { return prezime; }
    public void setPrezime(String prezime) { this.prezime = prezime; }

    public String getLicenca() { return licenca; }
    public void setLicenca(String licenca) { this.licenca = licenca; }

    public int getGodineIskustva() { return godineIskustva; }
    public void setGodineIskustva(int godineIskustva) { this.godineIskustva = godineIskustva; }

    public String getPunoIme() {
        return ime + " " + prezime;
    }
}
