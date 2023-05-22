package com.example.demo.dto;

import com.example.demo.entity.Korisnik;

public class RegisterDto extends LoginDto {
    private String ime;
    private String prezime;
    private String korisnickoIme;

    public RegisterDto() {
        super();
    }

    public RegisterDto(String email, String lozinka, String ime, String prezime, String korisnickoIme) {
        super(email, lozinka);
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
    }

    public RegisterDto(Korisnik korisnik) {
        super(korisnik);
        this.ime = korisnik.getIme();
        this.prezime = korisnik.getPrezime();
        this.korisnickoIme = korisnik.getKorisnickoIme();
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
}
