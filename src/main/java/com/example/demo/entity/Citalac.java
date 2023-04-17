package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Citalac extends Korisnik {
    @OneToOne(optional = false)
    private Polica want_to_read;

    @OneToOne(optional = false)
    private Polica currently_reading;

    @OneToOne(optional = false)
    private Polica read;

    @OneToMany(mappedBy = "citalac", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Polica> ostale_police = new HashSet<>();

    @OneToOne(mappedBy = "citalac")
    private Recenzija recenzija;

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

    public Recenzija getRecenzija() {
        return recenzija;
    }

    public void setRecenzija(Recenzija recenzija) {
        this.recenzija = recenzija;
    }
}
