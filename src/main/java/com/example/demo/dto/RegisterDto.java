package com.example.demo.dto;

import com.example.demo.entity.Korisnik;

public class RegisterDto extends LoginDto {
    private String email;

    public RegisterDto() {
        super();
    }

    public RegisterDto(String korisnickoIme, String email, String lozinka) {
        super(korisnickoIme, lozinka);
        this.email = email;
    }

    public RegisterDto(Korisnik korisnik) {
        super(korisnik);
        this.email = korisnik.getEmail();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
