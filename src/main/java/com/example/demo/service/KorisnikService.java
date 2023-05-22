package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class KorisnikService {

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

    public Citalac prijava(String korisnickoIme) {
        return citalacRepository.findByKorisnickoIme(korisnickoIme);
    }

    public void registracija(RegisterDto dto) {
        Citalac citalac = new Citalac();
        citalac.setIme(dto.getIme());
        citalac.setPrezime(dto.getPrezime());
        citalac.setKorisnickoIme(dto.getKorisnickoIme());
        citalac.setEmail(dto.getEmail());
        citalac.setLozinka(dto.getLozinka());
        citalac.setAdmin(false);
        citalacRepository.save(citalac);
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
