package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.entity.*;
import com.example.demo.service.KorisnikService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class Controller {
    @Autowired
    private KorisnikService korisnikService;

    @GetMapping("/")
    private String home() {
        return "Hello, World!"; //"<form action=\"/api/login\" method=\"POST\"><button name=\"korisnickoIme\" value=\"Login\"/></form>";
    }

//     curl http://localhost:8880/api/login -d '{"korisnickoIme":"Cutthroat", "lozinka":"test123"}'
    @PostMapping("/api/prijavi-se")
    public ResponseEntity prijava(@RequestBody LoginDto dto, HttpSession session) {
        Citalac citalac = korisnikService.prijava(dto.getEmail());

        if (citalac == null)
            return new ResponseEntity("Pogresno korisnicko ime.", HttpStatus.BAD_REQUEST);
        else if (citalac.getLozinka().equals(dto.getLozinka())) {
            session.setAttribute("citalac", citalac);
            // TODO treba da se prikaze lista polica nakon prijave
            return new ResponseEntity(citalac.toString(), HttpStatus.OK);
        }
        else
            return new ResponseEntity("Pogresna lozinka", HttpStatus.BAD_REQUEST);
    }

//    curl http://localhost:8880/api/register -d '{"korisnickoIme":"test","email":"test@test.test","lozinka":"test123"}'
//    TODO ponovljena email adresa i mora da bude jedinstvena, kao i korisnicko ime
    @PostMapping("/api/registruj-se")
    public void registracija(@RequestBody RegisterDto dto) {
        korisnikService.registracija(dto);
    }

    //     curl http://localhost:8880/api/pretrazi?pretraga=test
    @PostMapping("/api/pretrazi")
    public String pretrazi(@RequestParam String pretraga) {
        return korisnikService.pretrazi(pretraga).toString();
        // TODO bolji algoritam pretrazivanja
    }

//     curl http://localhost:8880/api/korisnici
    @GetMapping("/api/citaoci")
    public List<Citalac> listaCitalaca() {
        return korisnikService.listaCitaoca();
    }

    // TODO mozda dodati DTOs za slanje narednih stvari korisniku

    @GetMapping("/api/citaoci/{id}")
    public ResponseEntity jedanCitalac(@PathVariable Long id) {
        Optional<Citalac> citalac = korisnikService.jedanCitalac(id);
        if (!citalac.isPresent())
            return new ResponseEntity("Nepostojeci citalac.", HttpStatus.BAD_REQUEST);
        else
            return new ResponseEntity(citalac.get(), HttpStatus.OK);
    }

    @GetMapping("/api/zanrovi/{id}")
    public ResponseEntity jedanZanr(@PathVariable Long id) {
        Optional<Zanr> zanr = korisnikService.jedanZanr(id);
        if (!zanr.isPresent())
            return new ResponseEntity("Nepostojeci zanr.", HttpStatus.BAD_REQUEST);
        else
            return new ResponseEntity(zanr.get(), HttpStatus.OK);
    }

    @GetMapping("/api/recenzije/{id}")
    public ResponseEntity jednaRecenzija(@PathVariable Long id) {
        Optional<Recenzija> recenzija = korisnikService.jednaRecenzija(id);
        if (!recenzija.isPresent())
            return new ResponseEntity("Nepostojeca recenzija.", HttpStatus.BAD_REQUEST);
        else
            return new ResponseEntity(recenzija.get(), HttpStatus.OK);
    }

//     curl http://localhost:8880/api/podnesi-zahtev \
//     -d \
//     "{ \
//          'email':'test@test.test', \
//          'telefon':'+111 111 111', \
//          'poruka':'cao', \
//          'datum':'2023-05-10', \
//          'Autor':'test' \
//     }"

//    TODO proslediti dto servisu i tamo uraditi konverziju u non-dto objekat
    @PostMapping("/api/podnesi-zahtev")
    public void podnesiZahtev(@RequestBody ZahtevDto dto) {
        Autor autor = korisnikService.pronadjiAutora(dto.getImeAutora());

//        Zahtev se salje stiskom na taster SA profila autora sto znaci da
//        je autor vec kreiran i da ce ime uvek biti tacno i postojece!
//        if (autor == null)
//            return new ResponseEntity("Nepostojeci autor.", HttpStatus.BAD_REQUEST);
        Zahtev zahtev = new Zahtev();
        zahtev.setEmail(dto.getEmail());
        zahtev.setTelefon(dto.getTelefon());
        zahtev.setDatum(dto.getDatum());
        zahtev.setPoruka(dto.getPoruka());
        zahtev.setAutor(autor);
        korisnikService.sacuvajZahtev(zahtev);
    }


////////////////////////////////////////// CITALAC //////////////////////////////////////////
}
