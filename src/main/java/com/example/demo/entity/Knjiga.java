package com.example.demo.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
@Entity
public class Knjiga implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @Column(unique = true, nullable = false)
    private String naslov;
    @Column(unique = true)
    private String naslovna_fotografija;

    @Column(unique = true,nullable = false)
    private int ISBN;

    @Column(nullable = false)
    private Date datum_objavljivanja;

    @Column(nullable = false)
    private int broj_strana;

    @Column
    private String opis;

    @Column
    private float ocena;

    @Column(nullable = false)
    private String zanr; //ovo ce biti klasa i veza izmedju zanr i knjiga many to one

    public void setID(Long id) {
        this.ID = id;
    }

    public Long getID() {
        return ID;
    }


    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public String getNaslovna_fotografija() {
        return naslovna_fotografija;
    }

    public void setNaslovna_fotografija(String naslovna_fotografija) {
        this.naslovna_fotografija = naslovna_fotografija;
    }

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public Date getDatum_objavljivanja() {
        return datum_objavljivanja;
    }

    public void setDatum_objavljivanja(Date datum_objavljivanja) {
        this.datum_objavljivanja = datum_objavljivanja;
    }

    public int getBroj_strana() {
        return broj_strana;
    }

    public void setBroj_strana(int broj_strana) {
        this.broj_strana = broj_strana;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public float getOcena() {
        return ocena;
    }

    public void setOcena(float ocena) {
        this.ocena = ocena;
    }

    public String getZanr() {
        return zanr;
    }

    public void setZanr(String zanr) {
        this.zanr = zanr;
    }

    @Override
    public String toString() {
        return "Knjiga{" +
                "naslov='" + naslov + '\'' +
                ", datum_objavljivanja=" + datum_objavljivanja +
                ", ocena=" + ocena +
                ", zanr='" + zanr + '\'' +
                '}';
    }
}
