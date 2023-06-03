package com.example.demo.service;

import com.example.demo.entity.Knjiga;
import com.example.demo.entity.Recenzija;
import com.example.demo.repository.RecenzijaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RecenzijaService {
    @Autowired
    private RecenzijaRepository recenzijaRepository;

    public Recenzija findById(Long id) {
        return recenzijaRepository.findById(id).orElse(null);
    }

    public Recenzija save(Recenzija recenzija) {
        return recenzijaRepository.save(recenzija);
    }

    public void delete(Recenzija recenzija) {
        recenzijaRepository.delete(recenzija);
    }

    public List<Recenzija> findAll() {
        return recenzijaRepository.findAll();
    }

    public Recenzija dodajRecenziju(String tekst, Float ocena) {
        Recenzija recenzija = new Recenzija();
        recenzija.setTekst(tekst);
        recenzija.setDatumRecenzije(new Date());
        recenzija.setOcena(ocena);
        recenzija = save(recenzija);
        return recenzija;
    }

    public Integer obrisiRecenziju(Long id) {
        Recenzija recenzija = findById(id);

        if (recenzija == null) {
            return 1;
        }

        delete(recenzija);
        return 0;
    }
}
