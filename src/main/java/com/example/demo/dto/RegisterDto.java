package com.example.demo.dto;

import com.example.demo.entity.Korisnik;

public class RegisterDto extends LoginDto {
    private String ime;
    private String prezime;
    private String korisnickoIme;
    private String ponovljenEmail;

    public RegisterDto() {
        super();
    }

    public RegisterDto(String email, String lozinka, String ime, String prezime, String korisnickoIme, String ponovljenEmail) {
        super(email, lozinka);
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
        this.ponovljenEmail = ponovljenEmail;
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

    public String getPonovljenEmail() {
        return ponovljenEmail;
    }

    public void setPonovljenEmail(String ponovljenEmail) {
        this.ponovljenEmail = ponovljenEmail;
    }
}
