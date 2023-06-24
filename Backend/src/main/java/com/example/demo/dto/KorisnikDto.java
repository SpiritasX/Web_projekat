package com.example.demo.dto;

import com.example.demo.entity.Autor;
import com.example.demo.entity.Korisnik;
import com.example.demo.entity.Uloga;

import java.util.Date;

public class KorisnikDto {
    private Long id;
    private String ime;
    private String prezime;
    private String korisnickoIme;
    private Date datumRodjenja;
    private String profilnaSlika;
    private String opis;
    private String cookie;
    private String email;
    private Uloga uloga;
    private Boolean aktivan;

    public KorisnikDto(Korisnik korisnik) {
        this.id = korisnik.getId();
        this.ime = korisnik.getIme();
        this.prezime = korisnik.getPrezime();
        this.korisnickoIme = korisnik.getKorisnickoIme();
        this.profilnaSlika = korisnik.getProfilnaSlika();
        this.datumRodjenja = korisnik.getDatumRodjenja();
        this.opis = korisnik.getOpis();
        this.uloga = korisnik.getUloga();
        if (this.uloga.equals(Uloga.AUTOR))
            this.aktivan = ((Autor)korisnik).isAktivan();
    }

    public KorisnikDto(Korisnik korisnik, String cookie) {
        this.id = korisnik.getId();
        this.ime = korisnik.getIme();
        this.prezime = korisnik.getPrezime();
        this.korisnickoIme = korisnik.getKorisnickoIme();
        this.profilnaSlika = korisnik.getProfilnaSlika();
        this.datumRodjenja = korisnik.getDatumRodjenja();
        this.opis = korisnik.getOpis();
        this.cookie = cookie;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getProfilnaSlika() {
        return profilnaSlika;
    }

    public void setProfilnaSlika(String profilnaSlika) {
        this.profilnaSlika = profilnaSlika;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(Date datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Uloga getUloga() {
        return uloga;
    }

    public void setUloga(Uloga uloga) {
        this.uloga = uloga;
    }

    public Boolean getAktivan() {
        return aktivan;
    }

    public void setAktivan(Boolean aktivan) {
        this.aktivan = aktivan;
    }
}
