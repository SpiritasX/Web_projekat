package com.example.demo.controller;

import com.example.demo.entity.Korisnik;
import com.example.demo.entity.Uloga;
import com.example.demo.entity.Zanr;
import com.example.demo.service.ZanrService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/zanrovi")
public class ZanrController {
    @Autowired
    private ZanrService zanrService;

    @GetMapping("/")
    public ResponseEntity listaZanrova() {
        return new ResponseEntity(zanrService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity jedanZanr(@PathVariable Long id) {
        Zanr zanr = zanrService.findById(id);
        if (zanr == null) {
            return new ResponseEntity("Nepostojeci zanr", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(zanr, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity dodajZanr(@RequestParam String naziv, HttpSession session){
        Korisnik korisnik= (Korisnik) session.getAttribute("korisnik");

        if (korisnik == null) {
            return new ResponseEntity("Morate biti prijavljeni", HttpStatus.UNAUTHORIZED);
        }

        if(!korisnik.getUloga().equals(Uloga.ADMINISTRATOR)) {
            return new ResponseEntity("Morate biti administrator", HttpStatus.FORBIDDEN);
        }

        zanrService.dodajZanr(naziv);
        return new ResponseEntity("Uspesno dodat zanr", HttpStatus.OK);
    }

    @DeleteMapping("/{id}") // TODO dodati metodu za brisanje umesto delete
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

        zanrService.delete(zanr);
        return new ResponseEntity("Uspesno obrisan zanr", HttpStatus.OK);
    }
}
