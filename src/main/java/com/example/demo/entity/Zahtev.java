package com.example.demo.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
public class Zahtev implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String telefon;
    @Column(nullable = false)
    private String poruka;
    @Column(nullable = false)
    private Date datum;
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToOne
    @JoinColumn(unique = true, nullable = false)
    private Autor autor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Zahtev{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", telefon='" + telefon + '\'' +
                ", datum=" + datum +
                ", status=" + status +
                '}';
    }
}
