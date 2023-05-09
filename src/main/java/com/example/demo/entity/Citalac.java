package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Citalac extends Korisnik {
    @OneToOne(optional = false, mappedBy = "citalac")
    private Polica wantToRead;

    @OneToOne(optional = false, mappedBy = "citalac")
    private Polica currentlyReading;

    @OneToOne(optional = false, mappedBy = "citalac")
    private Polica read;

    @OneToMany
    @JoinColumn(name = "korisnik_id")
    private Set<Polica> ostalePolice = new HashSet<Polica>();

    public Polica getWantToRead() {
        return wantToRead;
    }

    public void setWantToRead(Polica wantToRead) {
        this.wantToRead = wantToRead;
    }

    public Polica getCurrentlyReading() {
        return currentlyReading;
    }

    public void setCurrentlyReading(Polica currentlyReading) {
        this.currentlyReading = currentlyReading;
    }

    public Polica getRead() {
        return read;
    }

    public void setRead(Polica read) {
        this.read = read;
    }

    public Set<Polica> getOstalePolice() {
        return ostalePolice;
    }

    public void setOstalePolice(Set<Polica> ostalePolice) {
        this.ostalePolice = ostalePolice;
    }

    @Override
    public String toString() {
        return "Citalac{" +
                super.toString() +
                "} ";
    }
}
