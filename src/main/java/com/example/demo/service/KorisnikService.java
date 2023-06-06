package com.example.demo.service;

import com.example.demo.dto.KnjigaDto;
import com.example.demo.dto.LoginDto;
import com.example.demo.dto.RegisterDto;
import com.example.demo.entity.*;
import com.example.demo.repository.AutorRepository;
import com.example.demo.repository.CitalacRepository;
import com.example.demo.repository.KorisnikRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class KorisnikService {
    @Autowired
    private KorisnikRepository korisnikRepository;
    @Autowired
    private CitalacRepository citalacRepository;
    @Autowired
    private AutorRepository autorRepository;
    @Autowired
    private PolicaService policaService;
    @Autowired
    private StavkaService stavkaService;

    public Korisnik findById(Long id) {
        return korisnikRepository.findById(id).orElse(null);
    }

    public Korisnik save(Korisnik korisnik) {
//        korisnik = korisnikRepository.save(korisnik);

        if (korisnik.getUloga().equals(Uloga.CITALAC)) {
            korisnik = citalacRepository.save((Citalac)korisnik);
        } else if (korisnik.getUloga().equals(Uloga.AUTOR)) {
            korisnik = autorRepository.save((Autor)korisnik);
        }

        return korisnik;
    }

    public void delete(Korisnik korisnik) {
        if (korisnik.getUloga().equals(Uloga.AUTOR)) {
            autorRepository.delete((Autor)korisnik);
        }

        if (korisnik.getUloga().equals(Uloga.CITALAC)) {
            citalacRepository.delete((Citalac)korisnik);
        }

        korisnikRepository.delete(korisnik);
    }

    public List<Korisnik> findAll() {
        return korisnikRepository.findAll();
    }

    public Korisnik findByEmail(String email) {
        return korisnikRepository.findByEmail(email).orElse(null);
    }

    public Korisnik findByKorisnickoIme(String korisnickoIme) {
        return korisnikRepository.findByKorisnickoIme(korisnickoIme).orElse(null);
    }

    public Korisnik prijava(LoginDto dto) {
        Korisnik korisnik = korisnikRepository.findByEmail(dto.getEmail()).orElse(null);
        if (korisnik != null && korisnik.getLozinka().equals(dto.getLozinka())) {
            return korisnik;
        }
        return null;
    }

    public void dodajPrimarnePolice(Citalac citalac) {
        Set<Polica> police = new HashSet<Polica>();
        police.add(policaService.dodajPolicu("Read",true));
        police.add(policaService.dodajPolicu("Currently Reading",true));
        police.add(policaService.dodajPolicu("Want To Read",true));
        citalac.setOstalePolice(police);
    }

    public void registracija(String ime, String prezime, String korisnickoIme, String email, String lozinka, Uloga uloga, Boolean dodajPrimarnePolice) {
        Citalac citalac = new Citalac();

        citalac.setIme(ime);
        citalac.setPrezime(prezime);
        citalac.setKorisnickoIme(korisnickoIme);
        citalac.setEmail(email);
        citalac.setLozinka(lozinka);
        citalac.setUloga(uloga);

        if (uloga.equals(Uloga.AUTOR)) {
            ((Autor)citalac).setAktivan(false);
        }

        if (dodajPrimarnePolice) {
            dodajPrimarnePolice(citalac);
        }

        save(citalac);
    }

    public Integer obrisiKorisnika(Long id) {
        Korisnik korisnik = findById(id);

        if (korisnik == null) {
            return 1;
        }

        if (korisnik.getUloga().equals(Uloga.ADMINISTRATOR)) {
            return 2;
        }

        if (korisnik.getUloga().equals(Uloga.AUTOR) && ((Autor)korisnik).getKnjige().size() > 0) {
            return 3;
        }

        Set<Polica> police = ((Citalac) korisnik).getOstalePolice();
        ((Citalac) korisnik).setOstalePolice(null);
        save(korisnik);
        for (Polica p : police) {
            for (Stavka s : p.getStavke()) {
                stavkaService.obrisiStavku(s);
            }
            policaService.delete(p);
        }
        delete(korisnik);
        return 0;
    }

    public void dodajPolicuKorisnika(Polica polica, Citalac citalac){
        Set<Polica> police = citalac.getOstalePolice();
        police.add(polica);
        citalac.setOstalePolice(police);
        citalacRepository.save(citalac);
    }

    public void azurirajAutora(Autor autor, String email, String lozinka) {
        autor.setEmail(email);
        autor.setLozinka(lozinka);
        save(autor);
    }
}
