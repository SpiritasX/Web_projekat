package com.example.demo.dto;

public class RegisterDto extends LoginDto {
    private String ime;
    private String prezime;
    private String korisnickoIme;
    private String ponovljenaLozinka;

    public RegisterDto() {
        super();
    }

    public RegisterDto(String email, String lozinka, String ime, String prezime, String korisnickoIme, String ponovljenaLozinka) {
        super(email, lozinka);
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
        this.ponovljenaLozinka = ponovljenaLozinka;
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

    public String getPonovljenaLozinka() {
        return ponovljenaLozinka;
    }

    public void setPonovljenaLozinka(String ponovljenaLozinka) {
        this.ponovljenaLozinka = ponovljenaLozinka;
    }
}
