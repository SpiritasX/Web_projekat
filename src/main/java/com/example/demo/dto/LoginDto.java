package com.example.demo.dto;

import com.example.demo.entity.Korisnik;

public class LoginDto {
    private String email;
    private String lozinka;

    public LoginDto() {

    }

    public LoginDto(String email, String lozinka) {
        this.email = email;
        this.lozinka = lozinka;
    }

    public LoginDto(Korisnik korisnik) {
        this.email = korisnik.getEmail();
        this.lozinka = korisnik.getLozinka();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }
}
