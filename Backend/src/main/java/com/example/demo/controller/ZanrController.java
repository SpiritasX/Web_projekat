package com.example.demo.controller;

import com.example.demo.entity.Knjiga;
import com.example.demo.entity.Korisnik;
import com.example.demo.entity.Uloga;
import com.example.demo.entity.Zanr;
import com.example.demo.service.KnjigaService;
import com.example.demo.service.ZanrService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.example.demo.repository.ZanrRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/zanrovi")
public class ZanrController {
    @Autowired
    private ZanrService zanrService;
    @Autowired
    private KnjigaService knjigaService;
    @Autowired
    private ZanrRepository zanrRepository;

    @GetMapping("/")
    public ResponseEntity listaZanrova() {
        List<String> zanrovi = new ArrayList<>();

        for (Zanr z : zanrService.findAll()) {
            zanrovi.add(z.getNaziv());
        }

        return new ResponseEntity(zanrovi, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity jedanZanr(@PathVariable Long id) {
        Zanr zanr = zanrService.findById(id);

        if (zanr == null) {
            return new ResponseEntity("Nepostojeci zanr", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(zanr.getNaziv(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity dodajZanr(@RequestParam String naziv, HttpSession session) {
        Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");

        if (korisnik == null) {
            return new ResponseEntity("Morate biti prijavljeni", HttpStatus.UNAUTHORIZED);
        }

        if (!korisnik.getUloga().equals(Uloga.ADMINISTRATOR)) {
            return new ResponseEntity("Morate biti administrator", HttpStatus.FORBIDDEN);
        }

        // return new ResponseEntity("Postoji zanr sa istim imenom, pokusajte ponovo.", HttpStatus.BAD_REQUEST);


        if (zanrService.findByNaziv(naziv) == null) {
            zanrService.dodajZanr(naziv);

            return new ResponseEntity("Uspesno dodat zanr", HttpStatus.OK);
        } else
            return new ResponseEntity<>("Zanr vec postoji, pokusajte ponovo.", HttpStatus.FORBIDDEN);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity obrisiZanr(@PathVariable Long id, HttpSession session) {
        Korisnik korisnik= (Korisnik) session.getAttribute("korisnik");

        if (korisnik == null) {
            return new ResponseEntity("Morate biti prijavljeni", HttpStatus.UNAUTHORIZED);
        }

        if(!korisnik.getUloga().equals(Uloga.ADMINISTRATOR)) {
            return new ResponseEntity("Morate biti administrator", HttpStatus.FORBIDDEN);
        }

        Zanr zanr = zanrService.findById(id);

        if (zanr == null) {
            return new ResponseEntity("Nepostojeci zanr", HttpStatus.BAD_REQUEST);
        }

        for (Knjiga k : knjigaService.findAll()) {
            if (k.getZanrovi().contains(zanr)) {
                return new ResponseEntity("Ne moze se obrisati zanr koji je u upotrebi", HttpStatus.FORBIDDEN);
            }
        }

        zanrService.obrisiZanr(zanr);

        return new ResponseEntity("Uspesno obrisan zanr", HttpStatus.OK);
    }
}
