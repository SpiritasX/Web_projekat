package com.example.demo.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)

public class Citalac extends Korisnik implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @OneToMany(mappedBy = "citalac", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Polica> want_to_read = new HashSet<>();
    @OneToMany(mappedBy = "citalac", fetch = FetchType.LAZY, cascade = CascadeType.ALL)

    private Set<Polica> currently_reading = new HashSet<>();
    @OneToMany(mappedBy = "citalac", fetch = FetchType.LAZY, cascade = CascadeType.ALL)

    private Set<Polica> read = new HashSet<>();
    @OneToMany(mappedBy = "citalac", fetch = FetchType.LAZY, cascade = CascadeType.ALL)

    private Set<Polica> ostale_police = new HashSet<>();

    public Set<Polica> getWant_to_read() {
        return want_to_read;
    }

    public void setWant_to_read(Set<Polica> want_to_read) {
        this.want_to_read = want_to_read;
    }

    public Set<Polica> getCurrently_reading() {
        return currently_reading;
    }

    public void setCurrently_reading(Set<Polica> currently_reading) {
        this.currently_reading = currently_reading;
    }

    public Set<Polica> getRead() {
        return read;
    }

    public void setRead(Set<Polica> read) {
        this.read = read;
    }

    public Set<Polica> getOstale_police() {
        return ostale_police;
    }

    public void setOstale_police(Set<Polica> ostale_police) {
        this.ostale_police = ostale_police;
    }
}
