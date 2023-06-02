package com.example.demo.service;

import com.example.demo.entity.Knjiga;
import com.example.demo.entity.Polica;
import com.example.demo.repository.KnjigaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class KnjigaService {
    @Autowired
    private KnjigaRepository knjigaRepository;

    public Knjiga findById(Long id) {
        Optional<Knjiga> knjiga = knjigaRepository.findById(id);
        if (knjiga.isPresent())
            return knjiga.get();
        return null;
    }

    public List<Knjiga> pretrazi(String pretraga) {
        return knjigaRepository.findAllByNaslov(pretraga);
    }

    public Knjiga dodajKnjigu(String nazivKnjige, String slika, String isbn, Date datum, Integer str) {
        Knjiga knjiga = new Knjiga();
        knjiga.setNaslovnaFotografija(slika);
        knjiga.setISBN(isbn);
        knjiga.setNaslov(nazivKnjige);
        knjiga.setDatumObjavljivanja(datum);
        knjiga = knjigaRepository.save(knjiga);
        return knjiga;
    }

    public void sacuvajKnjigu(Knjiga knjiga) {
        knjigaRepository.save(knjiga);
    }

    public Knjiga findByISBN(String ISBN) {
        Optional<Knjiga> knjiga = knjigaRepository.findByISBN(ISBN);
        if (knjiga.isPresent())
            return knjiga.get();
        return null;
    }

    public void delete(Knjiga knjiga) {
        knjigaRepository.delete(knjiga);
    }
}
