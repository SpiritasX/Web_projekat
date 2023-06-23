package com.example.demo.dto;
import com.example.demo.entity.Knjiga;
import com.example.demo.entity.Zanr;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class KnjigaDto {
    private Long id;
    private String naslov;
    private String ISBN;
    private Date datumObjavljivanja;
    private Integer brojStrana;
    private String opis;
    private Double ocena;
    private String naslovnaFotografija;
    private Set<Zanr> zanrovi;
    public KnjigaDto(){
        super();
    }

    public KnjigaDto(Knjiga knjiga) {
        this.id = knjiga.getId();
        this.naslov = knjiga.getNaslov();
        this.ISBN = knjiga.getISBN();
        this.datumObjavljivanja = knjiga.getDatumObjavljivanja();
        this.brojStrana = knjiga.getBrojStrana();
        this.opis = knjiga.getOpis();
        this.ocena = knjiga.getOcena();
        this.naslovnaFotografija = knjiga.getNaslovnaFotografija();
        this.zanrovi=knjiga.getZanrovi();

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

   // public MultipartFile getNaslovnaFotografija() {
     //   return naslovnaFotografija;
    //}


    public String getNaslovnaFotografija() {
        return naslovnaFotografija;
    }

    public void setNaslovnaFotografija(String naslovnaFotografija) {
        this.naslovnaFotografija = naslovnaFotografija;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Zanr> getZanrovi() {
        return zanrovi;
    }

    public void setZanrovi(Set<Zanr> zanrovi) {
        this.zanrovi = zanrovi;
    }


}
