package com.example.demo.entity;

import jakarta.persistence.*;

import java.io.Serializable;
@Entity
public class Stavka implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Polica polica;

    @OneToOne( cascade = CascadeType.ALL)
    @JoinColumn(name="recenzija_id",referencedColumnName = "id")
    private Recenzija recenzija;
    @OneToOne(mappedBy = "stavka")
    private Knjiga knjiga;

    public void setKnjiga(Knjiga knjiga) {
        this.knjiga = knjiga;
    }

    public Polica getPolica() {
        return polica;
    }

    public void setPolica(Polica polica) {
        this.polica = polica;
    }

    public Recenzija getRecenzija() {
        return recenzija;
    }

    public void setRecenzija(Recenzija recenzija) {
        this.recenzija = recenzija;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
