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

    public Korisnik findById(Long id) {
        return korisnikRepository.findById(id).orElse(null);
    }

    public Korisnik save(Korisnik korisnik) {
        korisnik = korisnikRepository.save(korisnik);

        if (korisnik.getUloga().equals(Uloga.CITALAC)) {
            korisnik = citalacRepository.save((Citalac)korisnik);
        }

        if (korisnik.getUloga().equals(Uloga.AUTOR)) {
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

    public Korisnik prijava(LoginDto dto) {
        Korisnik korisnik = korisnikRepository.findByEmail(dto.getEmail()).orElse(null);
        if (korisnik != null && korisnik.getLozinka().equals(dto.getLozinka())) {
            return korisnik;
        }
        return null;
    }

    public void registracija(RegisterDto dto) {
        Citalac citalac = new Citalac();

        citalac.setIme(dto.getIme());
        citalac.setPrezime(dto.getPrezime());
        citalac.setKorisnickoIme(dto.getKorisnickoIme());
        citalac.setEmail(dto.getEmail());
        citalac.setLozinka(dto.getLozinka());
        citalac.setUloga(Uloga.CITALAC);

        Set<Polica> police = new HashSet<Polica>();
        police.add(policaService.dodajPolicu("Read",true));
        police.add(policaService.dodajPolicu("Currently Reading",true));
        police.add(policaService.dodajPolicu("Want To Read",true));
        citalac.setOstalePolice(police);
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

        // TODO ne moze da se obrise autor koji ima knjige

        if (korisnik.getUloga().equals(Uloga.AUTOR)) {
            return 3;
        }

        delete(korisnik);
        return 0;
    }

    // TODO pomeriti mozda u policaService nekako
    public void dodajPolicu(Polica polica, Citalac citalac){
        Set<Polica> police = citalac.getOstalePolice();
        police.add(polica);
        citalac.setOstalePolice(police);
        citalacRepository.save(citalac);
    }

    // TODO razdvojiti kreiranje i aktiviranje?
    public Autor kreirajNalogAutora(String ime, String prezime, String korisnickoIme, String email, String lozinka) {
        Autor autor = new Autor();
        autor.setEmail(email);
        autor.setLozinka(lozinka);
        autor.setAktivan(true);
        autor.setUloga(Uloga.AUTOR);
        autor.setIme(ime);
        autor.setPrezime(prezime);
        autor.setKorisnickoIme(korisnickoIme);

        Set<Polica> police = new HashSet<Polica>();
        police.add(policaService.dodajPolicu("Read",true));
        police.add(policaService.dodajPolicu("Currently Reading",true));
        police.add(policaService.dodajPolicu("Want To Read",true));
        autor.setOstalePolice(police);
        autor = (Autor)save(autor);
        return autor;
    }

    public void azurirajAutora(Autor autor, String email, String lozinka) {
        autor.setEmail(email);
        autor.setLozinka(lozinka);
        save(autor);
    }
}
