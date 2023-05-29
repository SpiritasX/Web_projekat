package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Citalac extends Korisnik {
    @OneToMany
    @JoinColumn(name = "korisnik_id")
    private Set<Polica> ostalePolice = new HashSet<Polica>();

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
