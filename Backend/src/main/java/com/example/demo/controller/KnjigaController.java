package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.entity.*;
import com.example.demo.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.HandlerTypePredicate;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@RestController
@RequestMapping("/api/knjige")
public class KnjigaController {
    @Autowired
    private KnjigaService knjigaService;
    @Autowired
    private KorisnikService korisnikService;
    @Autowired
    private StavkaService stavkaService;
    @Autowired
    private KnjigaNaPoliciService knjigaNaPoliciService;

    @GetMapping("/")
    public ResponseEntity listaKnjiga() {
        List<Knjiga> knjige = knjigaService.findAll();
        List<KnjigaDto> dtos = new ArrayList();

        for (Knjiga k : knjige) {
            dtos.add(new KnjigaDto(k));
        }

        return new ResponseEntity(dtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity jednaKnjiga(@PathVariable Long id) {
        Knjiga knjiga = knjigaService.findById(id);

        if (knjiga == null) {
            return new ResponseEntity("Nepostojeca knjiga", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(new KnjigaDto(knjiga), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity dodajNovuKnjigu(@RequestBody KnjigaDto dto, @RequestPart (required = false) MultipartFile naslovnaFotografijaa, HttpSession session) {
        Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");

        if (korisnik == null) {
            return new ResponseEntity("Morate biti prijavljeni", HttpStatus.UNAUTHORIZED);
        }

        if (korisnik.getUloga().equals(Uloga.CITALAC)) {
            return new ResponseEntity("Niste autor ili administrator", HttpStatus.FORBIDDEN);
        }

        if (knjigaService.findByISBN(dto.getISBN()) != null) {
            return new ResponseEntity("Knjiga sa istim ISBN već postoji", HttpStatus.BAD_REQUEST);
        }

        // TODO PROVERITI fotografije moraju drugacije da se salju i njene adrese u fajlovima automatski da se dodaju
        // Postman form-data i promeni se u file
        knjigaService.dodajKnjigu(dto.getNaslov(),dto.getNaslovnaFotografija(),  dto.getISBN(), dto.getDatumObjavljivanja(), dto.getBrojStrana(), dto.getOpis());

        return new ResponseEntity<>("Uspesno dodata nova knjiga", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity azurirajKnjigu(@PathVariable Long id, @RequestBody KnjigaDto dto, @RequestPart(required = false) MultipartFile naslovnaFotografija, HttpSession session) {
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

        if (korisnik.getUloga().equals(Uloga.AUTOR) && !knjigaService.knjigaPripadaAutoru(knjiga, (Autor)korisnikService.findById(korisnik.getId()))) {
            return new ResponseEntity("Knjiga ne pripada vama", HttpStatus.FORBIDDEN);
        }

        knjiga = knjigaService.azurirajKnjigu(knjiga, dto.getNaslov(), naslovnaFotografija, dto.getISBN(), dto.getDatumObjavljivanja(), dto.getBrojStrana(), dto.getOpis());

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

        Knjiga knjiga = knjigaService.findById(id);

        if (knjiga == null) {
            return new ResponseEntity("Knjiga nije pronađena", HttpStatus.NOT_FOUND);
        }

        int kodGreske = knjigaService.obrisiKnjigu(knjiga);

        if (kodGreske == 1) {
            return new ResponseEntity("Knjiga ima recenzije i ne može biti obrisana", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity("Knjiga je uspešno obrisana", HttpStatus.OK);
    }

    @PostMapping("/{idKnjige}/polica/{idPolice}")
    public ResponseEntity dodajNaPolicu(@PathVariable Long idKnjige, @PathVariable Long idPolice, HttpSession session){
        Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");

        if (korisnik == null) {
            return new ResponseEntity("Morate biti prijavljeni", HttpStatus.UNAUTHORIZED);
        }

        int kodGreske = knjigaNaPoliciService.dodajKnjiguNaPolicu(korisnik.getId(), idKnjige, idPolice);

        switch (kodGreske) {
            case 1:
                return new ResponseEntity("Nepostojeca knjiga", HttpStatus.BAD_REQUEST);
            case 2:
                return new ResponseEntity("Nepostojeca polica", HttpStatus.BAD_REQUEST);
            case 3:
                return new ResponseEntity("Polica ne pripada dobrom citaocu", HttpStatus.FORBIDDEN);
            case 4:
                return new ResponseEntity("Knjiga se vec nalazi na jednoj primarnoj polici", HttpStatus.BAD_REQUEST);
            case 5:
                return new ResponseEntity("Knjiga mora da se nalazi na primarnoj polici da bi je dodali na obicnu", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity("Uspesno dodata knjiga na policu", HttpStatus.OK);
    }

    @DeleteMapping("/{idStavke}/polica/{idPolice}")
    public ResponseEntity obrisiSaPolice(@PathVariable Long idStavke, @PathVariable Long idPolice, HttpSession session) {
        Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");

        if (korisnik == null) {
            return new ResponseEntity("Morate biti prijavljeni", HttpStatus.UNAUTHORIZED);
        }

        int kodGreske = knjigaNaPoliciService.obrisiKnjiguSaPolice(korisnik.getId(), idStavke, idPolice);

        switch (kodGreske) {
            case 1:
                return new ResponseEntity("Nepostojeca stavka", HttpStatus.BAD_REQUEST);
            case 2:
                return new ResponseEntity("Nepostojeca polica", HttpStatus.BAD_REQUEST);
            case 3:
                return new ResponseEntity("Polica ne pripada dobrom citaocu", HttpStatus.BAD_REQUEST);
            case 4:
                return new ResponseEntity("Knjiga se ne nalazi na toj polici", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Uspesno obrisana knjiga sa police", HttpStatus.OK);
    }

    @PutMapping("/{idKnjige}/zanr/{idZanra}")
    public ResponseEntity dodajZanr(@PathVariable Long idKnjige, @PathVariable Long idZanra, HttpSession session) {
        Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");

        if (korisnik == null) {
            return new ResponseEntity("Morate biti prijavljeni", HttpStatus.UNAUTHORIZED);
        }

        Knjiga knjiga = knjigaService.findById(idKnjige);

        if (knjiga == null) {
            return new ResponseEntity("Nepostojeca knjiga", HttpStatus.BAD_REQUEST);
        }

        if (korisnik.getUloga().equals(Uloga.AUTOR) && !((Autor)korisnik).getKnjige().contains(knjiga)) {
            return new ResponseEntity("Ne mozete dodavati zanr na tudje knjige", HttpStatus.FORBIDDEN);
        }

        if (korisnik.getUloga().equals(Uloga.CITALAC)) {
            return new ResponseEntity("Morate biti autor knjige ili administrator", HttpStatus.FORBIDDEN);
        }

        int kodGreske = knjigaService.dodajZanr(knjiga, idZanra);

        switch (kodGreske) {
            case 1:
                return new ResponseEntity("Nepostojeci zanr", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity("Uspesno dodan zanr na knjigu", HttpStatus.OK);
    }

    @DeleteMapping("/{idKnjige}/zanr/{idZanra}")
    public ResponseEntity obrisiZanr(@PathVariable Long idKnjige, @PathVariable Long idZanra, HttpSession session) {
        Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");

        if (korisnik == null) {
            return new ResponseEntity("Morate biti prijavljeni", HttpStatus.UNAUTHORIZED);
        }

        Knjiga knjiga = knjigaService.findById(idKnjige);

        if (knjiga == null) {
            return new ResponseEntity("Nepostojeca knjiga", HttpStatus.BAD_REQUEST);
        }

        if (korisnik.getUloga().equals(Uloga.AUTOR) && !((Autor)korisnik).getKnjige().contains(knjiga)) {
            return new ResponseEntity("Ne mozete brisati zanr sa tudje knjige", HttpStatus.FORBIDDEN);
        }

        if (korisnik.getUloga().equals(Uloga.CITALAC)) {
            return new ResponseEntity("Morate biti autor knjige ili administrator", HttpStatus.FORBIDDEN);
        }

        int kodGreske = knjigaService.obrisiZanr(knjiga, idZanra);

        switch (kodGreske) {
            case 1:
                return new ResponseEntity("Nepostojeci zanr", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity("Uspesno obrisan zanr sa knjige", HttpStatus.OK);

    }
    @GetMapping("/{idKnjige}/recenzije")
    public ResponseEntity recenzijeKnjige(@PathVariable Long idKnjige, HttpSession session) {
        Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");

        if (korisnik == null) {
            return new ResponseEntity("Morate biti prijavljeni", HttpStatus.UNAUTHORIZED);
        }

        Knjiga knjiga = knjigaService.findById(idKnjige);

        if (knjiga == null) {
            return new ResponseEntity("Nepostojeca knjiga", HttpStatus.BAD_REQUEST);
        }
        List<RecenzijaDto>recenzijaDtos= new ArrayList<>();
        for(Stavka s: stavkaService.findAllByKnjigaId(idKnjige)){
           recenzijaDtos.add(new RecenzijaDto(s.getRecenzija()));
        }
        return new ResponseEntity(recenzijaDtos, HttpStatus.OK);
    }

}
