package com.example.demo.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Polica implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String naziv;
    @Column(nullable = false)
    private boolean primarna;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Set<Stavka> stavke = new HashSet<Stavka>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public boolean isPrimarna() {
        return primarna;
    }


    public void setPrimarna(boolean primarna) {
        this.primarna = primarna;
    }

    public Set<Stavka> getStavke() {
        return stavke;
    }

    public void setStavke(Set<Stavka> stavke) {
        this.stavke = stavke;
    }

    @Override
    public String toString() {
        return "Polica{" +
                "id=" + id +
                ", naziv='" + naziv + '\'' +
                ", primarna=" + primarna +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Polica))
            return false;

        Polica other = (Polica) o;

        return id != null &&
                id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
