package com.example.demo.entity;

import jakarta.persistence.*;

import java.io.Serializable;
@Entity
public class Stavka implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private Recenzija recenzija;

    @OneToOne(cascade = CascadeType.ALL)    // manytoone
    private Knjiga knjiga;

    public void setKnjiga(Knjiga knjiga) {
        this.knjiga = knjiga;
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

    @Override
    public String toString() {
        return "Stavka{" +
                "id=" + id +
                '}';
    }
}
