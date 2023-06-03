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

    public Stavka findByCitalacAndKnjiga(Citalac citalac, Knjiga knjiga) {
        for (Polica p : citalac.getOstalePolice()) {
            for (Stavka s : p.getStavke()) {
                if (s.getKnjiga().getId().equals(knjiga.getId())) {
                    return s;
                }
            }
        }
        return null;
    }

    // TODO dodavanje i brisanje stavke, ne treba da ima svoj kontroler ali mozda treba da se poziva metoda sa drugih mesta...
    public Stavka dodajStavku(Knjiga knjiga, Recenzija recenzija) {
        Stavka stavka = new Stavka();
        stavka.setKnjiga(knjiga);
        stavka.setRecenzija(recenzija);
        stavka = save(stavka);
        return stavka;
    }

    public Integer obrisiStavku(Long id) {
        Stavka stavka = findById(id);

        if (stavka == null) {
            return 1;
        }

        delete(stavka);
        return 0;
    }
}
