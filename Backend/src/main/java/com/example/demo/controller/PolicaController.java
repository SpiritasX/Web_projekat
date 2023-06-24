package com.example.demo.controller;

import com.example.demo.dto.PolicaDto;
import com.example.demo.entity.Citalac;
import com.example.demo.entity.Korisnik;
import com.example.demo.entity.Polica;
import com.example.demo.entity.Recenzija;
import com.example.demo.service.KorisnikService;
import com.example.demo.service.PolicaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/police")
public class PolicaController {
    @Autowired
    private PolicaService policaService;
    @Autowired
    private KorisnikService korisnikService;

    @GetMapping("/")
    public ResponseEntity listaPolica() {
        List<PolicaDto> police = new ArrayList<>();

        for (Polica p : policaService.findAll()) {
            police.add(new PolicaDto(p));
        }

        return new ResponseEntity(police, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity jednaPolica(@PathVariable Long id) {
        Polica polica = policaService.findById(id);

        if (polica == null) {
            return new ResponseEntity("Nepostojeca polica", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(new PolicaDto(polica), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity dodajPolicu(@RequestParam String nazivPolice, HttpSession session) {
        Korisnik korisnik = (Korisnik)session.getAttribute("korisnik");

        if (korisnik == null) {
            return new ResponseEntity<>("Morate biti prijavljeni", HttpStatus.UNAUTHORIZED);
        }

        for (Polica p : ((Citalac)korisnikService.findById(korisnik.getId())).getOstalePolice()) {
            if (p.getNaziv().equals(nazivPolice)) {
                return new ResponseEntity("Vec postoji polica sa tim nazivom", HttpStatus.BAD_REQUEST);
            }
        }

        Polica polica = policaService.dodajPolicu(nazivPolice, false);
        korisnikService.dodajPolicuKorisnika(polica, (Citalac)korisnikService.findById(korisnik.getId()));

        return new ResponseEntity<>("Polica " + nazivPolice + " uspesno dodana", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity azurirajPolicu(@PathVariable Long id, @RequestParam String noviNaziv, HttpSession session) {
        Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");

        if (korisnik == null) {
            return new ResponseEntity("Morate biti prijavljeni", HttpStatus.UNAUTHORIZED);
        }

        int kodGreske = policaService.azurirajPolicu((Citalac)korisnikService.findById(korisnik.getId()), id, noviNaziv);

        switch (kodGreske) {
            case 1:
                return new ResponseEntity("Nepostojeca polica", HttpStatus.BAD_REQUEST);
            case 2:
                return new ResponseEntity("Ta polica ne pripada vama", HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity("Uspesno azurirana polica", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity obrisiPolicu(@PathVariable Long id, HttpSession session) {
        Korisnik korisnik = (Korisnik)session.getAttribute("korisnik");

        if (korisnik == null) {
            return new ResponseEntity("Morate biti prijavljeni", HttpStatus.UNAUTHORIZED);
        }

        int kodGreske = policaService.obrisiPolicu(id, (Citalac)korisnikService.findById(korisnik.getId()));

        switch (kodGreske) {
            case 1:
                return new ResponseEntity("Nepostojeca polica", HttpStatus.BAD_REQUEST);
            case 2:
                return new ResponseEntity("Polica ne pripada vama", HttpStatus.FORBIDDEN);
            case 3:
                return new ResponseEntity("Ne moze se obrisati primarna polica", HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>("Uspesno obrisana polica", HttpStatus.OK);
    }

    // Pronadji policu po nazivu iz svojih polica
    @GetMapping("/ponazivu/{naziv}")
    public ResponseEntity findByNaziv(@PathVariable String naziv, HttpSession session) {
        Citalac citalac = (Citalac) session.getAttribute("korisnik");

        if (citalac == null) {
            return new ResponseEntity("Morate biti prijavljeni", HttpStatus.UNAUTHORIZED);
        }

        Polica polica = policaService.findByNaziv(naziv);

        if (!citalac.getOstalePolice().contains(polica)) {
            return new ResponseEntity("Nemate tu policu", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(new PolicaDto(polica), HttpStatus.OK);
    }
}
