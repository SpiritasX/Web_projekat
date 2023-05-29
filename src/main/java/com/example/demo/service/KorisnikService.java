package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class KorisnikService {

    @Autowired
    private PolicaRepository policaRepository;
    @Autowired
    private CitalacRepository citalacRepository;
    @Autowired
    private KnjigaRepository knjigaRepository;
    @Autowired
    private AutorRepository autorRepository;
    @Autowired
    private ZahtevRepository zahtevRepository;
    @Autowired
    private ZanrRepository zanrRepository;
    @Autowired
    private RecenzijaRepository recenzijaRepository;


    public Citalac prijava(String email) {
        return citalacRepository.findByEmail(email);
    }

    public void registracija(RegisterDto dto) {
        Citalac citalac = new Citalac();
        citalac.setIme(dto.getIme());
        citalac.setPrezime(dto.getPrezime());
        citalac.setKorisnickoIme(dto.getKorisnickoIme());
        citalac.setEmail(dto.getEmail());
        citalac.setLozinka(dto.getLozinka());
        citalac.setAdmin(false);

        Set<Polica> police = new HashSet<Polica>();
        police.add(PolicaService.dodajPolicu("Read"));
        police.add(PolicaService.dodajPolicu("Currently Reading"));
        police.add(PolicaService.dodajPolicu("Want To Read"));
        citalac.setOstalePolice(police);
        citalacRepository.save(citalac);
    }
    public List<Polica> listaPolica() {
        return policaRepository.findAll();
    //return policaRepository.findById(id);
    }


    public List<Citalac> listaCitaoca() {
        return citalacRepository.findAll();
    }

    public Optional<Citalac> jedanCitalac(Long id) {
        return citalacRepository.findById(id);
    }

    public Optional<Zanr> jedanZanr(Long id) {
        return zanrRepository.findById(id);
    }
    public Optional<Recenzija> jednaRecenzija(Long id) {
        return recenzijaRepository.findById(id);
    }

    public List<Knjiga> pretrazi(String pretraga) {
        return knjigaRepository.findAllByNaslov(pretraga);
    }

    public Autor pronadjiAutora(String ime) {
        return autorRepository.findByIme(ime);
    }

    public void sacuvajZahtev(Zahtev zahtev) {
        zahtevRepository.save(zahtev);
    }
}
