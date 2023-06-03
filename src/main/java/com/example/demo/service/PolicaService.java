package com.example.demo.service;

import com.example.demo.dto.KnjigaPolicaDto;
import com.example.demo.dto.RecenzijaDto;
import com.example.demo.entity.*;
import com.example.demo.repository.PolicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PolicaService {

    @Autowired
    private PolicaRepository policaRepository;
    @Autowired
    private KnjigaService knjigaService;
    @Autowired
    private StavkaService stavkaService;
    @Autowired
    private RecenzijaService recenzijaService;

    public Polica findById(Long id) {
        return policaRepository.findById(id).orElse(null);
    }

    public Polica save(Polica polica) {
        return policaRepository.save(polica);
    }

    public void delete(Polica polica) {
        policaRepository.delete(polica);
    }

    public List<Polica> findAll() {
        return policaRepository.findAll();
    }

    public Polica dodajPolicu(String naziv, Boolean primarna){
        Polica polica = new Polica();
        polica.setNaziv(naziv);
        polica.setPrimarna(primarna);
        polica = save(polica);
        return polica;
    }

    public Integer obrisiPolicu(Long id){
        Polica polica = findById(id);

        if (polica == null) {
            return 1;
        }

        if (polica.isPrimarna()) {
            return 2;
        }

        delete(polica);
        return 0;
    }

    public Boolean knjigaNaPrimarnojPolici(Knjiga knjiga, Citalac citalac) {
        for (Polica p : citalac.getOstalePolice())
            if (p.isPrimarna())
                for (Stavka s : p.getStavke())
                    if (s.getKnjiga().getId().equals(knjiga.getId()))
                        return true;
        return false;
    }

    public Boolean knjigaURead(Knjiga knjiga, Citalac citalac) {
        for (Polica p : citalac.getOstalePolice()) {
            if (p.getNaziv().equals("Read")) {
                for (Stavka s : p.getStavke()) {
                    if (s.getKnjiga().getId().equals(knjiga.getId())) {
                        return true;
                    }
                }
            }
        }
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

    public Integer dodajKnjiguNaPolicu(Citalac citalac, Long idKnjige, Long idPolice, RecenzijaDto dto) {
        Knjiga knjiga = knjigaService.findById(idKnjige);
        Polica polica = findById(idPolice);

        if (knjiga == null || polica == null) {
            return 1;
        }

        boolean sadrzi = false;
        for (Polica p : citalac.getOstalePolice()) {
            if (p.getId().equals(polica.getId())) {
                sadrzi = true;
            }
        }

        if (!sadrzi) {
            return 2;
        }

        if (knjigaNaPrimarnojPolici(knjiga, citalac) && polica.isPrimarna()) {
            return 3;
        }

        if (!knjigaNaPrimarnojPolici(knjiga, citalac) && !polica.isPrimarna()) {
            return 4;
        }

        if (!knjigaURead(knjiga, citalac) && dto != null) {
            return 5;
        }

        Stavka stavka = stavkaService.findByCitalacAndKnjiga(citalac, knjiga);

        if (stavka == null) {
            stavka = new Stavka();
            stavka.setKnjiga(knjiga);
        }

        if (dto != null) {
            stavka.setRecenzija(recenzijaService.dodajRecenziju(dto.getTekst(), dto.getOcena()));
        }

        stavka = stavkaService.save(stavka);

        dodajStavkuNaPolicu(stavka, polica);

        polica = policaRepository.save(polica);
        Set<Polica> police = citalac.getOstalePolice();
        for (Polica p : police) {
            if (p.getId().equals(polica.getId())) {
                police.remove(p);
                break;
            }
        }
        police.add(polica);

        return 0;
    }

    public Integer obrisiKnjiguSaPolice(Citalac citalac, Long idKnjige, Long idPolice) {
        Knjiga knjiga = knjigaService.findById(idKnjige);
        Polica polica = findById(idPolice);

        if (knjiga == null || polica == null) {
            return 1;
        }

        boolean sadrzi = false;
        for (Polica p : citalac.getOstalePolice()) {
            if (p.getId().equals(polica.getId())) {
                sadrzi = true;
            }
        }

        if (!sadrzi) {
            return 2;
        }

        Stavka stavka = stavkaService.findByCitalacAndKnjiga(citalac, knjiga);

        if (stavka == null) {
            return 3;
        }

        if (polica.isPrimarna()) {
            // TODO mozda moze da se obrise iz police automatski brisanjem stavke
            // TODO override equals metodu svih klasa da bi contains i remove i ostale metode radile lepo
            for (Polica p : citalac.getOstalePolice()) {
                for (Stavka s : p.getStavke()) {
                    if (s.getKnjiga().equals(knjiga)) {
                        p.getStavke().remove(s);
                        break;
                    }
                }
            }
            recenzijaService.delete(stavka.getRecenzija());
            stavkaService.delete(stavka);
        } else {
            for (Stavka s : polica.getStavke()) {
                if (s.getKnjiga().equals(knjiga)) {
                    polica.getStavke().remove(s);
                    break;
                }
            }
            save(polica);
        }

        return 0;
    }
}
