package com.example.demo.service;

import com.example.demo.dto.DodajKnjiguDto;
import com.example.demo.entity.*;
import com.example.demo.repository.PolicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Polica dodajPolicu(String po, Boolean primarna){
        Polica polica=new Polica();
        polica.setNaziv(po);
        polica.setPrimarna(primarna);
        policaRepository.save(polica);
        return polica;
    }

    public List<Polica> listaPolica() {
        return policaRepository.findAll();
    }

    public void obrisiPolicu(Long id){
        policaRepository.deleteById(id);
    }

    public Boolean knjigaNaPrimarnojPolici(Knjiga knjiga, Citalac citalac) {
        for (Polica p : citalac.getOstalePolice())
            if (p.isPrimarna())
                for (Stavka s : p.getStavke())
                    if (s.getKnjiga().equals(knjiga))
                        return true;
        return false;
    }

    public Integer dodajKnjiguNaPolicu(DodajKnjiguDto dto, Citalac citalac) {
        Optional<Knjiga> optionalKnjiga = knjigaService.findById(dto.getIdKnjige());
        Optional<Polica> optionalPolica = policaRepository.findById(dto.getIdPolice());
        Recenzija recenzija = null;

        if (!optionalKnjiga.isPresent() || !optionalPolica.isPresent())
            return 1;

        Knjiga knjiga = optionalKnjiga.get();
        Polica polica = optionalPolica.get();

        if (knjigaNaPrimarnojPolici(knjiga, citalac) && polica.isPrimarna())
            return 2;

        if (!knjigaNaPrimarnojPolici(knjiga, citalac) && !polica.isPrimarna())
            return 3;

        if (dto.getRecenzijaDto() != null && dto.getRecenzijaDto().getOcena() != null) {
            recenzija = new Recenzija();
            recenzija.setOcena(dto.getRecenzijaDto().getOcena());
            recenzija.setDatumRecenzije(dto.getRecenzijaDto().getDatumRecenzije());
            recenzija.setTekst(dto.getRecenzijaDto().getTekst());
            recenzija = recenzijaService.save(recenzija);
        }

        Stavka stavka = new Stavka();
        stavka.setKnjiga(knjiga);
        stavka.setRecenzija(recenzija);
        stavka = stavkaService.dodajStavku(stavka);

        Set<Stavka> stavke = polica.getStavke();
        if (stavke == null)
            stavke = new HashSet<>();
        stavke.add(stavka);
        polica.setStavke(stavke);
        polica = policaRepository.save(polica);

        return 0;
    }
}
