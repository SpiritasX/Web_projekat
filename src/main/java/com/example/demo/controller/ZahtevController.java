package com.example.demo.controller;

import com.example.demo.dto.ZahtevDto;
import com.example.demo.entity.*;
import com.example.demo.service.KorisnikService;
import com.example.demo.service.ZahtevService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/zahtevi")
public class ZahtevController {
    @Autowired
    private ZahtevService zahtevService;
    @Autowired
    private KorisnikService korisnikService;

    @GetMapping("/")
    public ResponseEntity listaZahteva(HttpSession session) {
        Korisnik korisnik = (Korisnik)session.getAttribute("korisnik");

        if (korisnik == null) {
            return new ResponseEntity("Morate biti prijavljeni", HttpStatus.UNAUTHORIZED);
        }

        if (!korisnik.getUloga().equals(Uloga.ADMINISTRATOR)) {
            return new ResponseEntity("Morate biti administrator", HttpStatus.FORBIDDEN);
        }

        List<ZahtevDto> zahtevi = new ArrayList<>();

        for (Zahtev z : zahtevService.findAll()) {
            zahtevi.add(new ZahtevDto(z));
        }

        return new ResponseEntity(zahtevi, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity jedanZahtev(@PathVariable Long id, HttpSession session) {
        Korisnik korisnik = (Korisnik)session.getAttribute("korisnik");

        if (korisnik == null) {
            return new ResponseEntity("Morate biti prijavljeni", HttpStatus.UNAUTHORIZED);
        }

        if (!korisnik.getUloga().equals(Uloga.ADMINISTRATOR)) {
            return new ResponseEntity("Morate biti administrator", HttpStatus.FORBIDDEN);
        }

        Zahtev zahtev = zahtevService.findById(id);

        if (zahtev == null) {
            return new ResponseEntity("Nepostojeci zahtev", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(new ZahtevDto(zahtev), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity podnesiZahtev(@RequestBody ZahtevDto dto) {
        Korisnik korisnik = korisnikService.findById(dto.getIdAutora());

        if (korisnik == null || !korisnik.getUloga().equals(Uloga.AUTOR)) {
            return new ResponseEntity("Nepostojeci autor", HttpStatus.BAD_REQUEST);
        }

        zahtevService.dodajZahtev(dto.getEmail(), dto.getTelefon(), dto.getPoruka(), (Autor)korisnik);
        return new ResponseEntity("Uspesno dodan zahtev", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity obradiZahtev(@PathVariable Long id, @RequestParam Boolean prihvati, HttpSession session) {
        Korisnik korisnik = (Korisnik)session.getAttribute("korisnik");

        if (korisnik == null) {
            return new ResponseEntity("Morate biti prijavljeni", HttpStatus.UNAUTHORIZED);
        }

        if (!korisnik.getUloga().equals(Uloga.ADMINISTRATOR)) {
            return new ResponseEntity("Morate biti administrator", HttpStatus.FORBIDDEN);
        }

        Zahtev zahtev = zahtevService.findById(id);

        if (zahtev == null) {
            return new ResponseEntity<>("Nepostojeci zahtev", HttpStatus.NOT_FOUND);
        }

        if (zahtev.getStatus().equals(Status.ODBIJEN) || zahtev.getStatus().equals(Status.ODOBREN)) {
            return new ResponseEntity<>("Zahtev je vec obradjen", HttpStatus.BAD_REQUEST);
        }

        zahtevService.obradiZahtev(zahtev, prihvati);

        return new ResponseEntity<>("Zahtev uspesno obradjen", HttpStatus.OK);
    }
}
