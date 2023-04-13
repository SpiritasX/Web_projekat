package com.example.demo.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
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
    private Zanr zanr;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Autor autor;

    @ManyToMany
    @JoinTable(name = "zanrovi_knjige",
            joinColumns = @JoinColumn(name = "knjiga_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "zanr_id", referencedColumnName = "id"))
    private Set<Zanr>zanrovi= new HashSet<>();

    public Set<Zanr> getZanrovi() {
        return zanrovi;
    }
    @OneToOne( cascade = CascadeType.ALL)
    @JoinColumn(name="stavka_id",referencedColumnName = "id")
    private Stavka stavka;

    public Stavka getStavka() {
        return stavka;
    }

    public void setStavka(Stavka stavka) {
        this.stavka = stavka;
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

    public Zanr getZanr() {
        return zanr;
    }

    public void setZanr(Zanr zanr) {
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
