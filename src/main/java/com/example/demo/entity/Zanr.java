package com.example.demo.entity;

import jakarta.persistence.*;

import java.io.Serializable;
@Entity
public class Zanr implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    public void setID(Long id) {
        this.ID = id;
    }

    public Long getID() {
        return ID;
    }

    @Column(nullable = false,unique = true)
     private String naziv;

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public String toString() {
        return "Zanr{" +
                "naziv='" + naziv + '\'' +
                '}';
    }
}
