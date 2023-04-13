package com.example.demo.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Administrator extends Korisnik implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @OneToMany(mappedBy = "administrator", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Zahtev> zahtevi = new HashSet<>();

    public Set<Zahtev> getZahtevi() {
        return zahtevi;
    }

    public void setZahtevi(Set<Zahtev> zahtevi) {
        this.zahtevi = zahtevi;
    }
}
