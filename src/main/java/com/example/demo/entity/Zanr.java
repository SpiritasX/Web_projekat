package com.example.demo.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Zanr implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    @Column(nullable = false, unique = true)
    private String naziv;

    @ManyToMany(mappedBy = "zanrovi")
//@JoinCOlumn(name="zanr_id")

    private Set<Knjiga> knjige = new HashSet<Knjiga>();

    public void setID(Long id) {
        this.ID = id;
    }

    public Long getID() {
        return ID;
    }

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
