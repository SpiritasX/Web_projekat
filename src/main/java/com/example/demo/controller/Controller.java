package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.entity.*;
import com.example.demo.service.KorisnikService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Enumeration;
import java.util.List;

@RestController
public class Controller {
    @Autowired
    private KorisnikService korisnikService;

    @GetMapping("/")
    private String home() {
        return "Hello, World!"; //"<form action=\"/api/login\" method=\"POST\"><button name=\"korisnickoIme\" value=\"Login\"/></form>";
    }

    // curl http://localhost:8880/api/login -H "Content-Type:application/json" -d '{"korisnickoIme":"Cutthroat", "lozinka":"test123"}'
    @PostMapping("/api/login")
    public ResponseEntity login(@RequestBody LoginDto dto, HttpSession session) {
        Citalac citalac = korisnikService.login(dto.getKorisnickoIme());

        if (citalac == null)
            return new ResponseEntity("Pogresno korisnicko ime.", HttpStatus.BAD_REQUEST);
        else if (citalac.getLozinka().equals(dto.getLozinka())) {
            session.setAttribute("citalac", citalac);
            return new ResponseEntity(citalac.toString(), HttpStatus.OK);
        }
        else
            return new ResponseEntity("Pogresna lozinka", HttpStatus.BAD_REQUEST);
    }

    // curl http://localhost:8880/api/register -H 'Content-Type: application/json' -d '{"korisnickoIme":"test","email":"test@test.test","lozinka":"test123"}'
    @PostMapping("/api/register")
    public void register(@RequestBody RegisterDto dto) {
        korisnikService.register(dto);
    }

    // curl http://localhost:8880/api/korisnici
    @GetMapping("/api/korisnici")
    public String listaCitalaca() {
        return korisnikService.listaCitaoca().toString();
    }

    // curl http://localhost:8880/api/pretrazi?pretraga=test
    @PostMapping("/api/pretrazi")
    public String pretrazi(@RequestParam String pretraga) {
        return korisnikService.pretrazi("korisnici", pretraga).toString();
        // TODO: neprijavljeni korisnik moze da pretrazuje samo knjige ali moze da vidi ostale korisnike, recenzije, autore,...
    }

    // curl http://localhost:8880/api/podnesi-zahtev
    // -H "Content-Type:application/json"
    // -d
    // "{
    //      'email':'test@test.test',
    //      'telefon':'+111 111 111',
    //      'poruka':'cao',
    //      'datum':'2023-05-10',
    //      'Autor':'test'
    // }"
    @PostMapping("/api/podnesi-zahtev")
    public void podnesiZahtev(@RequestBody Zahtev zahtev) {
        // TODO: ZahtevDto zato sto se salje ime autora koje treba da se konvertuje u id.
    }
}
