package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Autor extends Citalac {
    @Column(nullable = false)
    private Boolean aktivan;

    @OneToMany
    @JoinColumn(name = "autor_id")
    private Set<Knjiga> knjige = new HashSet<Knjiga>();

    public Boolean isAktivan() {
        return aktivan;
    }

    public void setAktivan(Boolean aktivan) {
        this.aktivan = aktivan;
    }

    @Override
    public String toString() {
        return "Autor{" +
                "aktivan=" + aktivan +
                "} " + super.toString();
    }
}

