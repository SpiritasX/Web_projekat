package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Citalac extends Korisnik {
    @OneToOne(optional = false, mappedBy = "citalac", targetEntity = Polica.class)
    private Polica want_to_read;

    @OneToOne(optional = false, mappedBy = "citalac", targetEntity = Polica.class)
    private Polica currently_reading;

    @OneToOne(optional = false, mappedBy = "citalac", targetEntity = Polica.class)
    private Polica read;

    @OneToMany(targetEntity = Polica.class)
    @JoinColumn(name = "korisnik_id")
    private Set<Polica> ostale_police = new HashSet<Polica>();

    @OneToMany(targetEntity = Recenzija.class)
    @JoinColumn(name = "korisnik_id")
    private Set<Recenzija> recenzije = new HashSet<Recenzija>();

    public Polica getWant_to_read() {
        return want_to_read;
    }

    public void setWant_to_read(Polica want_to_read) {
        this.want_to_read = want_to_read;
    }

    public Polica getCurrently_reading() {
        return currently_reading;
    }

    public void setCurrently_reading(Polica currently_reading) {
        this.currently_reading = currently_reading;
    }

    public Polica getRead() {
        return read;
    }

    public void setRead(Polica read) {
        this.read = read;
    }

    public Set<Polica> getOstale_police() {
        return ostale_police;
    }

    public void setOstale_police(Set<Polica> ostale_police) {
        this.ostale_police = ostale_police;
    }

    public Set<Recenzija> getRecenzije() {
        return recenzije;
    }

    public void setRecenzije(Set<Recenzija> recenzije) {
        this.recenzije = recenzije;
    }

    @Override
    public String toString() {
        return "Citalac{" +
                "} " + super.toString();
    }
}
