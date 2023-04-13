package com.example.demo.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;


@Entity
public class Autor extends Korisnik implements Serializable  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private boolean aktivan;
    //@Column
   // private ArrayList<Knjiga> spisakKnjiga;// nece da mi radi nesto kad ubacim listu knjiga

    public Autor(Long id, String ime, String prezime, String korisnickoIme, String email, String lozinka, Date datumRodjenja, String profilnaSlika, String opis, boolean admin) {
        super(id, ime, prezime, korisnickoIme, email, lozinka, datumRodjenja, profilnaSlika, opis,admin);
        this.id=id;
        this.aktivan=aktivan;
        //this.spisakKnjiga=new ArrayList<>();
    }

    public Autor() {
        super();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isAktivan() {
        return aktivan;
    }

    public void setAktivan(boolean aktivan) {
        this.aktivan = aktivan;
    }
}

