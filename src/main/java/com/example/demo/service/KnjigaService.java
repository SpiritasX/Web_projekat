package com.example.demo.service;

import com.example.demo.dto.KnjigaDto;
import com.example.demo.entity.Autor;
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
        return knjigaRepository.findById(id).orElse(null);
    }

    public Knjiga save(Knjiga knjiga) {
        return knjigaRepository.save(knjiga);
    }

    public void delete(Knjiga knjiga) {
        knjigaRepository.delete(knjiga);
    }

    public List<Knjiga> findAll() {
        return knjigaRepository.findAll();
    }

    public Knjiga findByISBN(String ISBN) {
        return knjigaRepository.findByISBN(ISBN).orElse(null);
    }

    public Knjiga dodajKnjigu(String naslov, String slika, String isbn, Date datum, Integer str) {
        Knjiga knjiga = new Knjiga();
        knjiga.setNaslovnaFotografija(slika);
        knjiga.setISBN(isbn);
        knjiga.setNaslov(naslov);
        knjiga.setDatumObjavljivanja(datum);
        knjiga.setBrojStrana(str);
        knjiga = save(knjiga);
        return knjiga;
    }

    public Integer obrisiKnjigu(Long id) {
        Knjiga knjiga = findById(id);

        if (knjiga == null) {
            return 1;
        }

        // ako knjiga ima recenzije ne moze biti obrisana
        //a ako ima recenzije onda ima ocenu pa proveravam sa ocenom jer oceni mogu direktno da pristupim
        if (knjiga.getOcena() > 0) {
            return 2;
        }

        delete(knjiga);
        return 0;
    }

    public Boolean knjigaPripadaAutoru(Knjiga knjiga, Autor autor) {
        for (Knjiga k : autor.getKnjige()) {
            if (knjiga.getId().equals(k.getId())) {
                return true;
            }
        }
        return false;
    }
}
