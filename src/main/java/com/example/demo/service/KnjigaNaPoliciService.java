package com.example.demo.service;

import com.example.demo.dto.RecenzijaDto;
import com.example.demo.entity.Citalac;
import com.example.demo.entity.Knjiga;
import com.example.demo.entity.Polica;
import com.example.demo.entity.Stavka;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class KnjigaNaPoliciService {
    @Autowired
    private PolicaService policaService;
    @Autowired
    private KnjigaService knjigaService;
    @Autowired
    private KorisnikService korisnikService;
    @Autowired
    private StavkaService stavkaService;

    public Boolean knjigaNaPrimarnojPolici(Knjiga knjiga, Citalac citalac) {
        for (Polica p : citalac.getOstalePolice())
            if (p.isPrimarna())
                for (Stavka s : p.getStavke())
                    if (s.getKnjiga().getId().equals(knjiga.getId()))
                        return true;
        return false;
    }

    public void dodajStavkuNaPolicu(Stavka stavka, Polica polica) {
        for (Stavka s : polica.getStavke()) {
            if (s.getId().equals(stavka.getId())) {
                return;
            }
        }
        polica.getStavke().add(stavka);
    }

    public Integer dodajKnjiguNaPolicu(Long idKorisnika, Long idKnjige, Long idPolice) {
        Citalac citalac = (Citalac) korisnikService.findById(idKorisnika);
        Knjiga knjiga = knjigaService.findById(idKnjige);

        if (knjiga == null) {
            return 1;
        }

        Polica polica = policaService.findById(idPolice);

        if (polica == null) {
            return 2;
        }

        if (!citalac.getOstalePolice().contains(polica)) {
            return 3;
        }

        Stavka stavka = stavkaService.findByCitalacAndKnjiga(citalac, knjiga);

        if (stavka == null) {
            stavka = stavkaService.dodajStavku(knjiga, null);
        }

        if (knjigaNaPrimarnojPolici(knjiga, citalac) && polica.isPrimarna()) {
            return 4;
        }

        if (!knjigaNaPrimarnojPolici(knjiga, citalac) && !polica.isPrimarna()) {
            return 5;
        }

        dodajStavkuNaPolicu(stavka, polica);

        policaService.save(polica);

        return 0;
    }

    public Integer obrisiKnjiguSaPolice(Long idKorisnika, Long idStavke, Long idPolice) {
        Citalac citalac = (Citalac) korisnikService.findById(idKorisnika);
        Stavka stavka = stavkaService.findById(idStavke);
        Polica polica = policaService.findById(idPolice);

        if (stavka == null) {
            return 1;
        }

        if (polica == null) {
            return 2;
        }

        if (!citalac.getOstalePolice().contains(polica)) {
            return 3;
        }

        if (!polica.getStavke().contains(stavka)) {
            return 4;
        }

        if (polica.isPrimarna()) {
            // TODO override equals metodu svih klasa da bi contains i remove i ostale metode radile lepo
            for (Polica p : citalac.getOstalePolice()) {
                p.getStavke().remove(stavka);
            }
            stavkaService.delete(stavka);
        } else {
            polica.getStavke().remove(stavka);
            // TODO ovo brise i stavku!!!
            policaService.save(polica);
        }

        return 0;
    }
}
