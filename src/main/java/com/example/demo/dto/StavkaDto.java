package com.example.demo.dto;

import com.example.demo.entity.Stavka;

public class StavkaDto {
    private RecenzijaDto recenzija;
    private KnjigaDto knjiga;

    public StavkaDto(Stavka stavka) {
        if (stavka != null) {
            this.recenzija = new RecenzijaDto(stavka.getRecenzija());
            this.knjiga = new KnjigaDto(stavka.getKnjiga());
        }
    }

    public RecenzijaDto getRecenzija() {
        return recenzija;
    }

    public void setRecenzija(RecenzijaDto recenzija) {
        this.recenzija = recenzija;
    }

    public KnjigaDto getKnjiga() {
        return knjiga;
    }

    public void setKnjiga(KnjigaDto knjiga) {
        this.knjiga = knjiga;
    }
}
