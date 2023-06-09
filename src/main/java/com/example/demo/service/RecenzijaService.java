package com.example.demo.service;

import com.example.demo.entity.*;
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
    @Autowired
    private StavkaService stavkaService;

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

    public Stavka knjigaURead(Long idKnjige, Citalac citalac) {
        for (Polica p : citalac.getOstalePolice()) {
            if (p.getNaziv().equals("Read")) {
                for (Stavka s : p.getStavke()) {
                    if (s.getKnjiga().getId().equals(idKnjige)) {
                        return s;
                    }
                }
            }
        }
        return null;
    }

    public Recenzija dodajRecenziju(Citalac citalac, String tekst, Float ocena, Long idKnjige) {
        Stavka stavka = knjigaURead(idKnjige, citalac);

        if (stavka == null) {
            return null;
        }

        Recenzija recenzija = new Recenzija();
        recenzija.setTekst(tekst);
        recenzija.setDatumRecenzije(new Date());
        recenzija.setOcena(ocena);
        recenzija = save(recenzija);

        stavka.setRecenzija(recenzija);
        stavkaService.save(stavka);
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

    public Recenzija azurirajRecenziju(Recenzija recenzija, Float ocena, String tekst, Date datumRecenzije) {
        if(ocena != null){
            recenzija.setOcena(ocena);
        }
        if(tekst !=null){
            recenzija.setTekst(tekst);
        }
        if(datumRecenzije != null){
            recenzija.setDatumRecenzije(datumRecenzije);
        }
        return recenzija;
    }
}
