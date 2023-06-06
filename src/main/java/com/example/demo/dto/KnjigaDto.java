package com.example.demo.dto;
import com.example.demo.entity.Knjiga;
import jakarta.persistence.Column;
import org.springframework.web.multipart.MultipartFile;
import org.yaml.snakeyaml.reader.StreamReader;

import java.io.File;
import java.io.FileReader;
import java.util.Date;

public class KnjigaDto {
    private String naslov;
    private String ISBN;
    private Date datumObjavljivanja;
    private Integer brojStrana;
    private String opis;
    private Double ocena;
    private String naslovnaFotografija;

    public KnjigaDto(){
        super();
    }

    public KnjigaDto(Knjiga knjiga) {
        this.naslov = knjiga.getNaslov();
        this.ISBN = knjiga.getISBN();
        this.datumObjavljivanja = knjiga.getDatumObjavljivanja();
        this.brojStrana = knjiga.getBrojStrana();
        this.opis = knjiga.getOpis();
        this.ocena = knjiga.getOcena();
        this.naslovnaFotografija = knjiga.getNaslovnaFotografija();
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public Date getDatumObjavljivanja() {
        return datumObjavljivanja;
    }

    public void setDatumObjavljivanja(Date datumObjavljivanja) {
        this.datumObjavljivanja = datumObjavljivanja;
    }

    public Integer getBrojStrana() {
        return brojStrana;
    }

    public void setBrojStrana(Integer brojStrana) {
        this.brojStrana = brojStrana;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Double getOcena() {
        return ocena;
    }

    public void setOcena(Double ocena) {
        this.ocena = ocena;
    }

    public String getNaslovnaFotografija() {
        return naslovnaFotografija;
    }

    public void setNaslovnaFotografija(String naslovnaFotografija) {
        this.naslovnaFotografija = naslovnaFotografija;
    }
}
