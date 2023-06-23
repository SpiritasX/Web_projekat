package com.example.demo.service;

import com.example.demo.dto.KnjigaDto;
import com.example.demo.dto.KorisnikDto;
import com.example.demo.dto.LoginDto;
import com.example.demo.dto.RegisterDto;
import com.example.demo.entity.*;
import com.example.demo.repository.AutorRepository;
import com.example.demo.repository.CitalacRepository;
import com.example.demo.repository.KorisnikRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
    private RecenzijaService recenzijaService;

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
        Korisnik korisnik;
        if (uloga.equals(Uloga.AUTOR))
            korisnik = new Autor();
        else
            korisnik = new Citalac();
        //        Citalac citalac = new Citalac();

        korisnik.setIme(ime);
        korisnik.setPrezime(prezime);
        korisnik.setKorisnickoIme(korisnickoIme);
        korisnik.setEmail(email);
        korisnik.setLozinka(lozinka);
        korisnik.setUloga(uloga);

        if (uloga.equals(Uloga.AUTOR)) {
            ((Autor)korisnik).setAktivan(false);
        }

        if (dodajPrimarnePolice) {
            dodajPrimarnePolice((Citalac)korisnik);
        }

        save(korisnik);
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

//        Set<Polica> police = ((Citalac) korisnik).getOstalePolice();
//        ((Citalac) korisnik).setOstalePolice(null);
//        save(korisnik);
//        for (Polica p : police) {
//            for (Stavka s : p.getStavke()) {
//                stavkaService.obrisiStavku(s);
//            }
//            policaService.delete(p);
//        }
//        for (Recenzija r : recenzijaService.findAllByCitalac((Citalac)korisnik)) {
//            r.setCitalac(null);
//            recenzijaService.save(r);
//        }
        recenzijaService.deleteAll(recenzijaService.findAllByCitalac((Citalac)korisnik));
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
