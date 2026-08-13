package com.tournament.model;

public class Igrac {

    private Long id;
    private String ime;
    private String prezime;
    private String pozicija;
    private int brojDresa;
    private Long timId;

    public Igrac() {}

    public Igrac(Long id, String ime, String prezime, String pozicija, int brojDresa, Long timId) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.pozicija = pozicija;
        this.brojDresa = brojDresa;
        this.timId = timId;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getIme() { return ime; }
    public void setIme(String ime) { this.ime = ime; }

    public String getPrezime() { return prezime; }
    public void setPrezime(String prezime) { this.prezime = prezime; }

    public String getPozicija() { return pozicija; }
    public void setPozicija(String pozicija) { this.pozicija = pozicija; }

    public int getBrojDresa() { return brojDresa; }
    public void setBrojDresa(int brojDresa) { this.brojDresa = brojDresa; }

    public Long getTimId() { return timId; }
    public void setTimId(Long timId) { this.timId = timId; }

    public String getPunoIme() {
        return ime + " " + prezime;
    }
}
