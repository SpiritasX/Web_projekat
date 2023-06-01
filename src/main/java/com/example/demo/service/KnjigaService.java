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
    public List<Knjiga> pretrazi(String pretraga) {
        return knjigaRepository.findAllByNaslov(pretraga);
    }

    public Knjiga dodajKnjigu(String na, String slika, String isbn, Date datum,Integer strane){
        Knjiga knjiga= new Knjiga();
        knjiga.setBrojStrana(strane);
        knjiga.setISBN(isbn);
        knjiga.setNaslov(na);
        knjiga.setDatumObjavljivanja(datum);
        knjiga.setNaslovnaFotografija(slika);
        return knjigaRepository.save(knjiga);
    }

    public Optional<Knjiga> findById(Long id) {
        return knjigaRepository.findById(id);
    }
}
