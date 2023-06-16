package com.example.demo.dto;

import com.example.demo.entity.Autor;
import com.example.demo.entity.Status;
import com.example.demo.entity.Zahtev;

import java.util.Date;

public class ZahtevDto {
    private Long id;
    private String email;
    private String telefon;
    private String poruka;
    private Long idAutora;

    private Status status;
    private Date datum;

    public ZahtevDto() {
    }

    public ZahtevDto(String email, String telefon, String poruka, Long idAutora) {
        this.email = email;
        this.telefon = telefon;
        this.poruka = poruka;
        this.idAutora = idAutora;
    }

    public ZahtevDto(Zahtev zahtev) {
        this.id = zahtev.getId();
        this.poruka = zahtev.getPoruka();
        this.telefon = zahtev.getTelefon();
        this.email = zahtev.getEmail();
        this.status = zahtev.getStatus();
        this.datum = zahtev.getDatum();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }

    public Long getIdAutora() {
        return idAutora;
    }

    public void setIdAutora(Long idAutora) {
        this.idAutora = idAutora;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
