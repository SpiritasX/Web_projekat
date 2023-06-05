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
    private Long id;
    @Column(nullable = false)
    private String naslov;
    @Column(nullable = false)
    private String naslovnaFotografija;
    @Column(unique = true, nullable = false)
    private String ISBN;
    @Column(nullable = false)
    private Date datumObjavljivanja;
    private Integer brojStrana;
    private String opis;
    private Double ocena;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
    @JoinTable(
            joinColumns = @JoinColumn(name = "knjiga_id"),
            inverseJoinColumns = @JoinColumn(name = "zanr_id")
    )
    private Set<Zanr> zanrovi = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public String getNaslovnaFotografija() {
        return naslovnaFotografija;
    }

    public void setNaslovnaFotografija(String naslovnaFotografija) {
        this.naslovnaFotografija = naslovnaFotografija;
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

    public Set<Zanr> getZanrovi() {
        return zanrovi;
    }

    public void setZanrovi(Set<Zanr> zanrovi) {
        this.zanrovi = zanrovi;
    }

    @Override
    public String toString() {
        return "Knjiga{" +
                "id=" + id +
                ", naslov='" + naslov + '\'' +
                ", ISBN=" + ISBN +
                ", datum_objavljivanja=" + datumObjavljivanja +
                ", broj_strana=" + brojStrana +
                ", zanrovi=" + zanrovi +
                '}';
    }
}
