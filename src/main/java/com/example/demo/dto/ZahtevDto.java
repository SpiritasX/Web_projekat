package com.example.demo.dto;

import com.example.demo.entity.Autor;
import com.example.demo.entity.Status;
import com.example.demo.entity.Zahtev;

import java.util.Date;

public class ZahtevDto {
    private String email;
    private String telefon;
    private String poruka;
    private Date datum;
    private Status status;
    private String imeAutora;

    public ZahtevDto() {
    }

    public ZahtevDto(String email, String telefon, String poruka, Date datum, Status status, String imeAutora) {
        this.email = email;
        this.telefon = telefon;
        this.poruka = poruka;
        this.datum = datum;
        this.status = status;
        this.imeAutora = imeAutora;
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

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getImeAutora() {
        return imeAutora;
    }

    public void setImeAutora(String imeAutora) {
        this.imeAutora = imeAutora;
    }



}
