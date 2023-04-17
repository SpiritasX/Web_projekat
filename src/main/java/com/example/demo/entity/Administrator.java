package com.example.demo.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Administrator extends Korisnik {
    @OneToMany(mappedBy = "administrator", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Zahtev> zahtevi = new HashSet<>();

    public Set<Zahtev> getZahtevi() {
        return zahtevi;
    }

    public void setZahtevi(Set<Zahtev> zahtevi) {
        this.zahtevi = zahtevi;
    }
}
