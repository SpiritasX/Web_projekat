package com.example.demo.controller;

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

@RestController
@RequestMapping("/api/police")
public class PolicaController {
    @Autowired
    private PolicaService policaService;
    @Autowired
    private KorisnikService korisnikService;

    @GetMapping("/")
    public ResponseEntity listaPolica() {
        return new ResponseEntity(policaService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity jednaPolica(@PathVariable Long id) {
        Polica polica = policaService.findById(id);
        if (polica == null) {
            return new ResponseEntity("Nepostojeca recenzija", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(polica, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity dodajPolicu(@RequestParam String nazivPolice, HttpSession session) {
        Korisnik korisnik = (Korisnik)session.getAttribute("korisnik");
        if (korisnik == null) {
            return new ResponseEntity<>("Morate biti prijavljeni", HttpStatus.UNAUTHORIZED);
        }

        // TODO ako si prijaljen kao administrator, izazvaces server error :)

        for (Polica p : ((Citalac)korisnik).getOstalePolice()) {
            if (p.getNaziv().equals(nazivPolice)) {
                return new ResponseEntity("Vec postoji polica sa tim nazivom", HttpStatus.BAD_REQUEST);
            }
        }

        Polica polica = policaService.dodajPolicu(nazivPolice, false);
        korisnikService.dodajPolicu(polica, (Citalac)korisnik);
        return new ResponseEntity<>("Polica " + nazivPolice + " uspesno dodana", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity azurirajPolicu(@PathVariable Long id, HttpSession session) {
        return new ResponseEntity("TODO", HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity obrisiPolicu(@PathVariable Long id, HttpSession session) {
        Korisnik korisnik = (Korisnik)session.getAttribute("korisnik");

        if (korisnik == null) {
            return new ResponseEntity("Morate biti prijaljeni", HttpStatus.UNAUTHORIZED);
        }

        int rezultat = policaService.obrisiPolicu(id);
        korisnikService.save(korisnik);

        if (rezultat == 1) {
            return new ResponseEntity("Nepostojeca polica", HttpStatus.BAD_REQUEST);
        }

        if (rezultat == 2) {
            return new ResponseEntity("Ne moze se obrisati primarna polica", HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>("Uspesno obrisana polica", HttpStatus.OK);
    }
}
