package com.example.demo.service;

import com.example.demo.entity.Knjiga;
import com.example.demo.repository.KnjigaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class KnjigaService {
    @Autowired
    private KnjigaRepository knjigaRepository;
    public List<Knjiga> pretrazi(String pretraga) {
        return knjigaRepository.findAllByNaslov(pretraga);
    }

    public Knjiga dodajKnjigu(String nazivKnjige, String slika, String isbn, Date datum, Integer str) {
        Knjiga knjiga= new Knjiga();
        knjiga.setNaslovnaFotografija(slika);
        knjiga.setISBN(isbn);
        knjiga.setNaslov(nazivKnjige);
        knjiga.setDatumObjavljivanja(datum);
        knjigaRepository.save(knjiga);
        return knjiga;
    }
}
