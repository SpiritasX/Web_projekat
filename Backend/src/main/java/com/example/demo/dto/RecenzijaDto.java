package com.example.demo.dto;

import com.example.demo.entity.Recenzija;

import java.util.Date;

public class RecenzijaDto {
    private Float ocena;
    private String tekst;
    private Date datumRecenzije;

    public RecenzijaDto(Float ocena, String tekst) {
        this.ocena = ocena;
        this.tekst = tekst;
    }

    public RecenzijaDto(Recenzija recenzija) {
        if (recenzija != null) {
            this.ocena = recenzija.getOcena();
            this.tekst = recenzija.getTekst();
            this.datumRecenzije = recenzija.getDatumRecenzije();
        }
    }

    public Float getOcena() {
        return ocena;
    }

    public void setOcena(Float ocena) {
        this.ocena = ocena;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public Date getDatumRecenzije() {
        return datumRecenzije;
    }

    public void setDatumRecenzije(Date datumRecenzije) {
        this.datumRecenzije = datumRecenzije;
    }
}
