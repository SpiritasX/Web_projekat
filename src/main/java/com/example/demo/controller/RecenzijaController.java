package com.example.demo.controller;

import com.example.demo.dto.KnjigaDto;
import com.example.demo.dto.RecenzijaDto;
import com.example.demo.entity.*;
import com.example.demo.service.KnjigaService;
import com.example.demo.service.KorisnikService;
import com.example.demo.service.RecenzijaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/recenzije")
public class RecenzijaController {
    @Autowired
    private RecenzijaService recenzijaService;
    @Autowired
    private KorisnikService korisnikService;
    @Autowired
    private KnjigaService knjigaService;

    @GetMapping("/")
    public ResponseEntity listaRecenzija() {
        List<RecenzijaDto> recenzije = new ArrayList<>();

        for (Recenzija r : recenzijaService.findAll()) {
            recenzije.add(new RecenzijaDto(r));
        }

        return new ResponseEntity(recenzije, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity jednaRecenzija(@PathVariable Long id) {
        Recenzija recenzija = recenzijaService.findById(id);

        if (recenzija == null) {
            return new ResponseEntity("Nepostojeca recenzija", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(new RecenzijaDto(recenzija), HttpStatus.OK);
    }

    @PostMapping("/{idKnjige}")
    public ResponseEntity dodajRecenziju(@PathVariable Long idKnjige, @RequestBody RecenzijaDto dto, HttpSession session){
        Korisnik korisnik= (Korisnik) session.getAttribute("korisnik");

        if (korisnik == null) {
            return new ResponseEntity("Morate biti prijavljeni", HttpStatus.UNAUTHORIZED);
        }

        Citalac citalac = (Citalac)korisnikService.findById(korisnik.getId());
        Recenzija recenzija = recenzijaService.dodajRecenziju(citalac, dto.getTekst(), dto.getOcena(), idKnjige);

        if (recenzija == null) {
            return new ResponseEntity("Recenzija se ne nalazi u Read polici", HttpStatus.FORBIDDEN);
        }

        knjigaService.azurirajOcenuKnjige(idKnjige);

        return new ResponseEntity("Uspesno dodana recenzija", HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity azurirajRecenziju(@PathVariable Long id, @RequestBody RecenzijaDto dto, HttpSession session){
        Korisnik korisnik = (Korisnik)session.getAttribute("korisnik");

        if (korisnik == null) {
            return new ResponseEntity("Morate biti prijavljeni", HttpStatus.UNAUTHORIZED);
        }
        Recenzija recenzija= recenzijaService.findById(id);
        if (recenzija == null) {
            return new ResponseEntity("Recenzija nije pronadjena", HttpStatus.NOT_FOUND);

        }
        recenzija=recenzijaService.azurirajRecenziju(recenzija,dto.getOcena(), dto.getTekst(), dto.getDatumRecenzije());

        recenzijaService.save(recenzija);
        return new ResponseEntity("Recenzija je uspešno ažurirana", HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity obrisiRecenziju(@PathVariable Long id, HttpSession session) {
        Korisnik korisnik= (Korisnik) session.getAttribute("korisnik");

        if (korisnik == null) {
            return new ResponseEntity("Morate biti prijavljeni", HttpStatus.UNAUTHORIZED);
        }

        int kodGreske = recenzijaService.obrisiRecenziju(id);

        switch (kodGreske) {
            case 1:
                return new ResponseEntity("Nepostojeca recenzija", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity("Uspesno obrisana recenzija", HttpStatus.OK);
    }
}
