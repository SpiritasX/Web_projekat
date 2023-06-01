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
                    if (s.getKnjiga().equals(knjiga))
                        return true;
        return false;
    }

    // TODO treba da postoji samo jedna stavka koja predstavlja jednu knjigu i recenziju nekog korisnika umesto da se pravi nova svaki put kad se ista knjiga dodaje
    public Integer dodajKnjiguNaPolicu(KnjigaPolicaDto dto, Citalac citalac) {
        Optional<Knjiga> optionalKnjiga = knjigaService.findById(dto.getIdKnjige());
        Optional<Polica> optionalPolica = policaRepository.findById(dto.getIdPolice());
        Recenzija recenzija = null;

        if (!optionalKnjiga.isPresent() || !optionalPolica.isPresent())
            return 1;

        Knjiga knjiga = optionalKnjiga.get();
        Polica polica = optionalPolica.get();

        if (!citalac.getOstalePolice().contains(polica))
            return 2;

        if (knjigaNaPrimarnojPolici(knjiga, citalac) && polica.isPrimarna())
            return 3;

        if (!knjigaNaPrimarnojPolici(knjiga, citalac) && !polica.isPrimarna())
            return 4;

        if (polica.getNaziv().equals("Read") && dto.getRecenzijaDto() == null)
            return 5;

        if (dto.getRecenzijaDto().getOcena() == null)
            return 6;

        if (dto.getRecenzijaDto().getOcena() != null) {
            recenzija = new Recenzija();
            recenzija.setOcena(dto.getRecenzijaDto().getOcena());
            recenzija.setDatumRecenzije(dto.getRecenzijaDto().getDatumRecenzije());
            recenzija.setTekst(dto.getRecenzijaDto().getTekst());
            recenzija = recenzijaService.save(recenzija);
        }

        Stavka stavka = new Stavka();
        stavka.setKnjiga(knjiga);
        stavka.setRecenzija(recenzija);
        stavka = stavkaService.save(stavka);

        Set<Stavka> stavke = polica.getStavke();
        if (stavke == null)
            stavke = new HashSet<>();
        stavke.add(stavka);
        polica.setStavke(stavke);
        polica = policaRepository.save(polica);

        return 0;
    }

    public Integer obrisiKnjiguSaPolice(@RequestBody KnjigaPolicaDto dto, Citalac citalac) {
        Optional<Knjiga> optionalKnjiga = knjigaService.findById(dto.getIdKnjige());
        Optional<Polica> optionalPolica = policaRepository.findById(dto.getIdPolice());
        Recenzija recenzija = null;

        if (!optionalKnjiga.isPresent() || !optionalPolica.isPresent())
            return 1;

        Knjiga knjiga = optionalKnjiga.get();
        Polica polica = optionalPolica.get();

        if (!citalac.getOstalePolice().contains(polica))
            return 2;

        if (polica.isPrimarna()) {
            for (Polica p : citalac.getOstalePolice())
                for (Stavka s : p.getStavke())
                    if (s.getKnjiga().equals(knjiga)) {
                        recenzijaService.delete(s.getRecenzija());
                        stavkaService.delete(s);
                        break;
                    }
        } else {
            for (Stavka s : polica.getStavke())
                if (s.getKnjiga().equals(knjiga)) {
                    stavkaService.delete(s);
                    break;
                }
        }

        return 0;
    }
}
