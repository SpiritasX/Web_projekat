package com.example.demo.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Recenzija implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Float ocena;
    private String tekst;
    @Column(nullable = false)
    private Date datumRecenzije;

    @ManyToOne
    @JoinColumn(name = "korisnik_id")
    private Citalac citalac;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getOcena() {
        return ocena;
    }

    public void setOcena(Float ocena) {
        this.ocena = ocena;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public Date getDatumRecenzije() {
        return datumRecenzije;
    }

    public void setDatumRecenzije(Date datumRecenzije) {
        this.datumRecenzije = datumRecenzije;
    }

//    TODO zakomentarisati sve gettere i settere veza
//    Zbog njih nastaje beskonacna rekurzija pri ispisu
//    JSON objekata u responsu!!!

//    public Citalac getCitalac() {
//        return citalac;
//    }
//
//    public void setCitalac(Citalac citalac) {
//        this.citalac = citalac;
//    }

    @Override
    public String toString() {
        return "Recenzija{" +
                "id=" + id +
                ", ocena=" + ocena +
                ", datumRecenzije=" + datumRecenzije +
                '}';
    }
}
