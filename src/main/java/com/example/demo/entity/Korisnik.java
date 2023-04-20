package com.example.demo.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GeneratorType;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
public abstract class Korisnik implements Serializable {
    @Id
//    @SequenceGenerator(name = "mySeqGenV1")
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGenV1")
    @GeneratedValue
    private Long id;
    private String ime;
    private String prezime;
    @Column(unique = true, nullable = false)
    private String korisnickoIme;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String lozinka;
    private Date datumRodjenja;
    private String profilnaSlika;
    private String opis;
    @Column(nullable = false)
    private Boolean admin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(Date datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public String getProfilnaSlika() {
        return profilnaSlika;
    }

    public void setProfilnaSlika(String profilnaSlika) {
        this.profilnaSlika = profilnaSlika;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "Korisnik{" +
                "id=" + id +
                ", korisnickoIme='" + korisnickoIme + '\'' +
                '}';
    }
}
