package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.entity.*;
import com.example.demo.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/knjige")
public class KnjigaController {
    @Autowired
    private KnjigaService knjigaService;
    @Autowired
    private PolicaService policaService;
    @Autowired
    private KorisnikService korisnikService;

    @GetMapping("/")
    public ResponseEntity listaKnjiga() {
        return new ResponseEntity(knjigaService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity jednaKnjiga(@PathVariable Long id) {
        Knjiga knjiga = knjigaService.findById(id);
        if (knjiga == null) {
            return new ResponseEntity("Nepostojeca knjiga", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(knjiga, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity dodajNovuKnjigu(@RequestBody KnjigaDto dto, HttpSession session) {
        Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");

        if (korisnik == null) {
            return new ResponseEntity("Morate biti prijavljeni", HttpStatus.UNAUTHORIZED);
        }

        if (korisnik.getUloga().equals(Uloga.CITALAC)) {
            return new ResponseEntity("Niste autor ili administrator", HttpStatus.FORBIDDEN);
        }

        if (knjigaService.findByISBN(dto.getISBN()) != null) {
            return new ResponseEntity("Knjiga sa istim ISBN već postoji", HttpStatus.FORBIDDEN);
        }

        // TODO fotografije moraju drugacije da se salju i njene adrese u fajlovima automatski da se dodaju
        knjigaService.dodajKnjigu(dto.getNaslov(), dto.getNaslovnaFotografija(), dto.getISBN(), dto.getDatumObjavljivanja(), dto.getBrojStrana());
        return new ResponseEntity<>("Uspesno dodata nova knjiga", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity azurirajKnjigu(@PathVariable Long id, @RequestBody KnjigaDto dto, HttpSession session) {
        Korisnik korisnik = (Korisnik)session.getAttribute("korisnik");

        if (korisnik == null) {
            return new ResponseEntity("Morate biti prijavljeni", HttpStatus.UNAUTHORIZED);
        }

        if (korisnik.getUloga().equals(Uloga.CITALAC)) {
            return new ResponseEntity("Morate biti autor ili administrator da bi ste azurirali knjigu", HttpStatus.FORBIDDEN);
        }

        Knjiga knjiga = knjigaService.findById(id);

        if (knjiga == null) {
            return new ResponseEntity<>("Knjiga nije pronađena", HttpStatus.NOT_FOUND);
        }

        if (!knjigaService.knjigaPripadaAutoru(knjiga, (Autor)korisnik)) {
            return new ResponseEntity("Knjiga ne pripada vama", HttpStatus.FORBIDDEN);
        }

        if (dto.getBrojStrana() != null) {
            knjiga.setBrojStrana(dto.getBrojStrana());
        }

        if (dto.getISBN() != null) {
            knjiga.setISBN(dto.getISBN());
        }

        if (dto.getNaslov() != null) {
            knjiga.setNaslov(dto.getNaslov());
        }

        if (dto.getOpis() != null) {
            knjiga.setOpis(dto.getOpis());
        }

        if (dto.getDatumObjavljivanja() != null) {
            knjiga.setDatumObjavljivanja(dto.getDatumObjavljivanja());
        }

        if (dto.getNaslovnaFotografija() != null) {
            knjiga.setNaslovnaFotografija(dto.getNaslovnaFotografija());
        }

        knjigaService.save(knjiga);
        return new ResponseEntity("Knjiga je uspešno ažurirana", HttpStatus.OK);
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity obrisiKnjigu(@PathVariable Long id, HttpSession session) {
        Korisnik korisnik = (Korisnik)session.getAttribute("korisnik");

        if (korisnik == null) {
            return new ResponseEntity("Morate biti prijavljeni", HttpStatus.UNAUTHORIZED);
        }

        if (korisnik.getUloga().equals(Uloga.CITALAC)) {
            return new ResponseEntity("Morate biti autor ili administrator da bi ste obrisali knjigu", HttpStatus.FORBIDDEN);
        }

        int rezultat = knjigaService.obrisiKnjigu(id);

        if (rezultat == 1) {
            return new ResponseEntity("Knjiga nije pronađena", HttpStatus.NOT_FOUND);
        }

        if (rezultat == 2) {
            return new ResponseEntity("Knjiga ima recenzije i ne može biti obrisana", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity("Knjiga je uspešno obrisana", HttpStatus.OK);
    }

    @PostMapping("/{idKnjige}/polica/{idPolice}")
    public ResponseEntity dodajNaPolicu(@PathVariable Long idKnjige, @PathVariable Long idPolice, @RequestBody RecenzijaDto dto, HttpSession session){
        Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");

        if (korisnik == null) {
            return new ResponseEntity("Morate biti prijavljeni", HttpStatus.UNAUTHORIZED);
        }

        int rezultat = policaService.dodajKnjiguNaPolicu((Citalac)korisnik, idKnjige, idPolice, dto);
        korisnikService.save(korisnik);
        // TODO da li bi bolje bilo cuvati samo id jer ce onda uvek biti osvezen objekat sacuvan unutar sesije?
        session.setAttribute("korisnik", korisnik);

        if (rezultat == 1) {
            return new ResponseEntity("Knjiga ili polica ne postoji", HttpStatus.BAD_REQUEST);
        }

        if (rezultat == 2) {
            return new ResponseEntity("Polica ne pripada dobrom citaocu", HttpStatus.FORBIDDEN);
        }

        if (rezultat == 3) {
            return new ResponseEntity("Knjiga se vec nalazi na jednoj primarnoj polici", HttpStatus.BAD_REQUEST);
        }

        if (rezultat == 4) {
            return new ResponseEntity("Knjiga mora da se nalazi na primarnoj polici da bi je dodali na obicnu", HttpStatus.BAD_REQUEST);
        }

        if (rezultat == 5) {
            return new ResponseEntity("Ne moze se dodati recenzija, knjiga nije u Read polici", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity("Uspesno dodata knjiga na policu", HttpStatus.OK);
    }

    @DeleteMapping("/{idKnjige}/polica/{idPolice}")
    public ResponseEntity obrisiSaPolice(@PathVariable Long idKnjige, @PathVariable Long idPolice, HttpSession session) {
        Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");

        if (korisnik == null) {
            return new ResponseEntity("Morate biti prijavljeni", HttpStatus.UNAUTHORIZED);
        }

        int rezultat = policaService.obrisiKnjiguSaPolice((Citalac)korisnik, idKnjige, idPolice);

        if (rezultat == 1) {
            return new ResponseEntity("Knjiga ili polica ne postoji", HttpStatus.BAD_REQUEST);
        }

        if (rezultat == 2) {
            return new ResponseEntity("Polica ne pripada dobrom citaocu", HttpStatus.BAD_REQUEST);
        }

        if (rezultat == 3) {
            return new ResponseEntity("Knjiga se ne nalazi na toj polici", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity("Uspesno obrisana knjiga sa police", HttpStatus.OK);
    }
}
