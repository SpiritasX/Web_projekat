package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.repository.PolicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PolicaService {

    @Autowired
    private PolicaRepository policaRepository;

    public Polica findById(Long id) {
        return policaRepository.findById(id).orElse(null);
    }

    public Polica findByNaziv(String naziv) {
        return policaRepository.findByNaziv(naziv).orElse(null);
    }

    public Polica save(Polica polica) {
        return policaRepository.save(polica);
    }

    public void delete(Polica polica) {
        policaRepository.delete(polica);
    }

    public List<Polica> findAll() {
        return policaRepository.findAll();
    }

    public Polica dodajPolicu(String naziv, Boolean primarna){
        Polica polica = new Polica();
        polica.setNaziv(naziv);
        polica.setPrimarna(primarna);
        polica = save(polica);
        return polica;
    }

    public Integer azurirajPolicu(Citalac citalac, Long id, String naziv) {
        Polica polica = findById(id);

        if (polica == null) {
            return 1;
        }

        if (!citalac.getOstalePolice().contains(polica)) {
            return 2;
        }

        polica.setNaziv(naziv);
        save(polica);

        return 0;
    }

    public Integer obrisiPolicu(Long id, Citalac citalac){
        Polica polica = findById(id);

        if (polica == null) {
            return 1;
        }

        if (!citalac.getOstalePolice().contains(polica)) {
            return 2;
        }

        if (polica.isPrimarna()) {
            return 3;
        }

        polica.setStavke(null);
        save(polica);
        citalac.getOstalePolice().remove(polica);
        delete(polica);

        return 0;
    }
}
