package com.example.demo.controller;

import com.example.demo.dto.RecenzijaDto;
import com.example.demo.entity.Korisnik;
import com.example.demo.entity.Recenzija;
import com.example.demo.entity.Uloga;
import com.example.demo.entity.Zanr;
import com.example.demo.service.RecenzijaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/recenzije")
public class RecenzijaController {
    @Autowired
    private RecenzijaService recenzijaService;

    @GetMapping("/")
    public ResponseEntity listaRecenzija() {
        return new ResponseEntity(recenzijaService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity jednaRecenzija(@PathVariable Long id) {
        Recenzija recenzija = recenzijaService.findById(id);
        if (recenzija == null) {
            return new ResponseEntity("Nepostojeca recenzija", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(recenzija, HttpStatus.OK);
    }

    // TODO azurirati avg ocenu knjige pri dodavanju recenzije
    @PostMapping("/") // TODO OBRISI DATUM IZ DTO
    public ResponseEntity dodajRecenziju(@RequestBody RecenzijaDto dto, HttpSession session){
        Korisnik korisnik= (Korisnik) session.getAttribute("korisnik");

        if (korisnik == null) {
            return new ResponseEntity("Morate biti prijavljeni", HttpStatus.UNAUTHORIZED);
        }

        recenzijaService.dodajRecenziju(dto.getTekst(), dto.getOcena());
        return new ResponseEntity("Uspesno dodana recenzija", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity obrisiRecenziju(@PathVariable Long id, HttpSession session) {
        Korisnik korisnik= (Korisnik) session.getAttribute("korisnik");

        if (korisnik == null) {
            return new ResponseEntity("Morate biti prijavljeni", HttpStatus.UNAUTHORIZED);
        }

        int rezultat = recenzijaService.obrisiRecenziju(id);

        if (rezultat == 1) {
            return new ResponseEntity("Nepostojeca recenzija", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity("Uspesno obrisana recenzija", HttpStatus.OK);
    }
}
