package com.example.demo.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Knjiga implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    @Column(nullable = false)
    private String naslov;
    @Column(nullable = false)
    private String naslovna_fotografija;
    @Column(unique = true, nullable = false)
    private String ISBN;
    @Column(nullable = false)
    private Date datum_objavljivanja;
    private Long broj_strana;
    private String opis;
    private Double ocena;

    @ManyToMany
    private Set<Zanr> zanrovi = new HashSet<>();

    public Set<Zanr> getZanrovi() {
        return zanrovi;
    }

    public void setZanrovi(Set<Zanr> zanrovi) {
        this.zanrovi = zanrovi;
    }

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

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public Date getDatum_objavljivanja() {
        return datum_objavljivanja;
    }

    public void setDatum_objavljivanja(Date datum_objavljivanja) {
        this.datum_objavljivanja = datum_objavljivanja;
    }

    public Long getBroj_strana() {
        return broj_strana;
    }

    public void setBroj_strana(Long broj_strana) {
        this.broj_strana = broj_strana;
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

    @Override
    public String toString() {
        return "Knjiga{" +
                "ID=" + ID +
                ", naslov='" + naslov + '\'' +
                ", ISBN=" + ISBN +
                ", datum_objavljivanja=" + datum_objavljivanja +
                ", broj_strana=" + broj_strana +
                ", zanrovi=" + zanrovi +
                '}';
    }
}
