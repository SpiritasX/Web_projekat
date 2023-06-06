package com.example.demo.dto;

import com.example.demo.entity.Polica;
import com.example.demo.entity.Stavka;

import java.util.HashSet;
import java.util.Set;

public class PolicaDto {
    private String naziv;
    private Set<StavkaDto> stavke;

    public PolicaDto(Polica polica) {
        if (polica != null) {
            this.naziv = polica.getNaziv();
            stavke = new HashSet<>();
            for (Stavka s : polica.getStavke()) {
                stavke.add(new StavkaDto(s));
            }
        }
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Set<StavkaDto> getStavke() {
        return stavke;
    }

    public void setStavke(Set<StavkaDto> stavke) {
        this.stavke = stavke;
    }
}
