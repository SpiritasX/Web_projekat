package com.example.demo.service;

import com.example.demo.entity.Autor;
import com.example.demo.entity.Knjiga;
import com.example.demo.dto.KnjigaDto;
import com.example.demo.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
public class AutorService {
    @Autowired
    private AutorRepository autorRepository;

    public void azurirajAutora(Autor a) {
        autorRepository.save(a);
    }

    public Autor pronadjiAutora(String ime) {
        return autorRepository.findByIme(ime);
    }
    public void dodajKnjigu(Knjiga k,Autor a){
        Set<Knjiga>knjige;
        knjige=a.getKnjige();
        knjige.add(k);
        a.setKnjige(knjige);
        azurirajAutora(a);
    }

    public void dodajKnjiguDTO(KnjigaDto dto) {
        Knjiga knjiga=new Knjiga();

        knjiga.setISBN(dto.getISBN());
        knjiga.setNaslov(dto.getNaslov());
        knjiga.setDatumObjavljivanja(dto.getDatumObjavljivanja());
        knjiga.setBrojStrana(dto.getBrojStrana());
        knjiga.setOpis(dto.getOpis());
        knjiga.setNaslovnaFotografija(dto.getNaslovnaFotografija());

    }



}
