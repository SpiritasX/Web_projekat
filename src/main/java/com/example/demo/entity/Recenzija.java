package com.example.demo.entity;

import com.sun.jdi.PrimitiveValue;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Recenzija implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    @Column(nullable = false)
    private float ocena;
    private String tekst;
    @Column(nullable = false)
    private Date datum_recenzije;

    public void setID(Long id) {
        this.ID = id;
    }

    public Long getID() {
        return ID;
    }

    public float getOcena() {
        return ocena;
    }

    public void setOcena(float ocena) {
        this.ocena = ocena;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public Date getDatum_recenzije() {
        return datum_recenzije;
    }

    public void setDatum_recenzije(Date datum_recenzije) {
        this.datum_recenzije = datum_recenzije;
    }

    @Override
    public String toString() {
        return "Recenzija{" +
                "ocena=" + ocena +
                '}';
    }
}
