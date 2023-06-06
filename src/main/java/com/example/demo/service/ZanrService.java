package com.example.demo.service;

import com.example.demo.entity.Knjiga;
import com.example.demo.entity.Zanr;
import com.example.demo.repository.ZanrRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ZanrService {
    @Autowired
    private ZanrRepository zanrRepository;

    public Zanr findById(Long id) {
        return zanrRepository.findById(id).orElse(null);
    }

    public Zanr save(Zanr zanr) {
        return zanrRepository.save(zanr);
    }

    public void delete(Zanr zanr) {
        zanrRepository.delete(zanr);
    }

    public List<Zanr> findAll() {
        return zanrRepository.findAll();
    }

    public Zanr dodajZanr(String naziv){
        Zanr zanr = new Zanr();
        zanr.setNaziv(naziv);
        zanr = save(zanr);
        return zanr;
    }

    public void obrisiZanr(Zanr zanr) {
        delete(zanr);
    }
}
