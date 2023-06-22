package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.repository.StavkaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StavkaService {
    @Autowired
    private StavkaRepository stavkaRepository;
    @Autowired
    private PolicaService policaService;
//    @Autowired
//    private RecenzijaService recenzijaService;

    public Stavka findById(Long id) {
        return stavkaRepository.findById(id).orElse(null);
    }

    public Stavka save(Stavka stavka) {
        return stavkaRepository.save(stavka);
    }

    public void delete(Stavka stavka) {
        stavkaRepository.delete(stavka);
    }

    public List<Stavka> findAll() {
        return stavkaRepository.findAll();
    }
    public List<Stavka>findAllByKnjigaId(Long KnjigaID){return stavkaRepository.findAllByKnjigaId(KnjigaID);}

    public Stavka findByCitalacAndKnjiga(Citalac citalac, Knjiga knjiga) {
        for (Polica p : citalac.getOstalePolice()) {
            for (Stavka s : p.getStavke()) {
                if (s.getKnjiga().equals(knjiga)) {
                    return s;
                }
            }
        }
        return null;
    }

    public Stavka dodajStavku(Knjiga knjiga, Recenzija recenzija) {
        Stavka stavka = new Stavka();
        stavka.setKnjiga(knjiga);
        stavka.setRecenzija(recenzija);
        stavka = save(stavka);
        return stavka;
    }

    public Integer obrisiStavku(Stavka stavka) {
        for (Polica p : policaService.findAll()) {
            for (Stavka s : p.getStavke()) {
                if (s.equals(stavka)) {
                    p.getStavke().remove(s);
                    break;
                }
            }
        }

//        recenzijaService.delete(stavka.getRecenzija());
        delete(stavka);

        return 0;
    }
}
