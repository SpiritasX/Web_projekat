package com.example.demo.service;

import com.example.demo.dto.KnjigaPolicaDto;
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

    public Polica dodajPolicu(String naziv, Boolean primarna){
        Polica polica = new Polica();
        polica.setNaziv(naziv);
        polica.setPrimarna(primarna);
        policaRepository.save(polica);
        return polica;
    }

    public List<Polica> listaPolica() {
        return policaRepository.findAll();
    }

    public Integer obrisiPolicu(Long id){
        Optional<Polica> polica = policaRepository.findById(id);

        if (!polica.isPresent())
            return 1;

        if (polica.get().isPrimarna())
            return 2;

        policaRepository.deleteById(id);
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

    // TODO azuriranje polica u korisniku??
    public Integer dodajKnjiguNaPolicu(KnjigaPolicaDto dto, Citalac citalac) {
        Knjiga knjiga = knjigaService.findById(dto.getIdKnjige());
        Optional<Polica> optionalPolica = policaRepository.findById(dto.getIdPolice());
        Recenzija recenzija = null;
        Stavka stavka = null;

        if (knjiga == null || !optionalPolica.isPresent())
            return 1;

        Polica polica = optionalPolica.get();

        boolean sadrzi = false;
        for (Polica p : citalac.getOstalePolice()) {
            if (p.getId().equals(polica.getId())) {
                sadrzi = true;
            }
        }
        if (!sadrzi)
            return 2;

        for (Polica p : citalac.getOstalePolice()) {
            for (Stavka s : p.getStavke()) {
                if (s.getKnjiga().getId().equals(knjiga.getId())) {
                    stavka = s;
                    break;
                }
            }
        }

        if (stavka == null) {
            stavka = new Stavka();
            stavka.setKnjiga(knjiga);
        }

        if (knjigaNaPrimarnojPolici(knjiga, citalac) && polica.isPrimarna())
            return 3;

        if (!knjigaNaPrimarnojPolici(knjiga, citalac) && !polica.isPrimarna())
            return 4;

//        if (polica.getNaziv().equals("Read") && dto.getRecenzijaDto() == null)
//            return 5;

        if (dto.getRecenzijaDto() != null && dto.getRecenzijaDto().getOcena() == null)
            return 6;

        if (dto.getRecenzijaDto() != null) {
            recenzija = new Recenzija();
            recenzija.setOcena(dto.getRecenzijaDto().getOcena());
            recenzija.setDatumRecenzije(dto.getRecenzijaDto().getDatumRecenzije());
            recenzija.setTekst(dto.getRecenzijaDto().getTekst());
            recenzija = recenzijaService.save(recenzija);
        }

        stavka.setRecenzija(recenzija);
        stavka = stavkaService.save(stavka);

        sadrzi = false;
        Set<Stavka> stavke = polica.getStavke();
        for (Stavka s : stavke) {
            if (s.getId().equals(stavka.getId())) {
                sadrzi = true;
                break;
            }
        }
        if (!sadrzi)
            stavke.add(stavka);
        polica.setStavke(stavke);
        polica = policaRepository.save(polica);
        Set<Polica> police = citalac.getOstalePolice();
        for (Polica p : police) {
            if (p.getId().equals(polica.getId())) {
                police.remove(p);
                break;
            }
        }
        police.add(polica);
        citalac.setOstalePolice(police);

        return 0;
    }

    public Integer obrisiKnjiguSaPolice(@RequestBody KnjigaPolicaDto dto, Citalac citalac) {
        Knjiga knjiga = knjigaService.findById(dto.getIdKnjige());
        Optional<Polica> optionalPolica = policaRepository.findById(dto.getIdPolice());
        Recenzija recenzija = null;
        Stavka stavka = null;

        if (knjiga == null || !optionalPolica.isPresent())
            return 1;

        Polica polica = optionalPolica.get();

        if (!citalac.getOstalePolice().contains(polica))
            return 2;

        for (Polica p : citalac.getOstalePolice()) {
            for (Stavka s : p.getStavke()) {
                if (s.getKnjiga().equals(knjiga)) {
                    stavka = s;
                    break;
                }
            }
        }

        if (stavka == null)
            return 3;

        if (polica.isPrimarna()) {
            for (Polica p : citalac.getOstalePolice()) {
                for (Stavka s : p.getStavke()) {
                    if (s.getKnjiga().equals(knjiga)) {
                        recenzijaService.delete(s.getRecenzija());
                        stavkaService.delete(s);
                        break;
                    }
                }
            }
        } else {
            Set<Stavka> stavke = polica.getStavke();
            stavke.remove(stavka);
            polica.setStavke(stavke);
            policaRepository.save(polica);
        }

        return 0;
    }
}
