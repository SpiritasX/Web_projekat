package com.example.demo.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Polica implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String naziv;
    @Column(nullable = false)
    private boolean primarna;

    @OneToOne
    @JoinColumn(name = "korisnik_id")
    private Citalac citalac;

    @OneToMany
    @JoinColumn(name = "polica_id")
    private Set<Stavka> stavke = new HashSet<Stavka>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public boolean isPrimarna() {
        return primarna;
    }

    public void setPrimarna(boolean primarna) {
        this.primarna = primarna;
    }

    public Set<Stavka> getStavke() {
        return stavke;
    }

    public void setStavke(Set<Stavka> stavke) {
        this.stavke = stavke;
    }

    @Override
    public String toString() {
        return "Polica{" +
                "id=" + id +
                ", naziv='" + naziv + '\'' +
                ", primarna=" + primarna +
                '}';
    }
}
