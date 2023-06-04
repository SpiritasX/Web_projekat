package com.example.demo.controller;

import com.example.demo.dto.LoginDto;
import com.example.demo.dto.RegisterDto;
import com.example.demo.entity.Citalac;
import com.example.demo.entity.Korisnik;
import com.example.demo.entity.Uloga;
import com.example.demo.service.KorisnikService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/korisnici")
public class KorisnikController {
    @Autowired
    private KorisnikService korisnikService;

    @GetMapping("")
    public List<Korisnik> listaKorisnika() {
        return korisnikService.findAll();
    }

    // TODO Koristiti DTO za slanje svega!!!!

    @GetMapping("/{id}")
    public ResponseEntity jedanKorisnik(@PathVariable Long id) {
        Korisnik korisnik = korisnikService.findById(id);
        if (korisnik != null) {
            return new ResponseEntity(korisnik, HttpStatus.OK);
        }
        return new ResponseEntity("Nepostojeci korisnik", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("prijavi-se")
    public ResponseEntity prijava(@RequestBody LoginDto dto, HttpSession session) {
        if (dto.getEmail() == null || dto.getLozinka() == null) {
            return new ResponseEntity("Moraju biti popunjena sva polja", HttpStatus.BAD_REQUEST);
        }

        Korisnik korisnik = korisnikService.prijava(dto);

        if (korisnik == null) {
            return new ResponseEntity("Pogresan email ili lozinka", HttpStatus.BAD_REQUEST);
        }

        session.setAttribute("korisnik", korisnik);
        return new ResponseEntity("Uspesno prijavljen", HttpStatus.OK);
    }

    // TODO ponovljena email adresa i mora da bude jedinstvena, kao i korisnicko ime
    @PostMapping("registruj-se") // TODO ispraviti da radi u postmanu
    public ResponseEntity registracija(@RequestBody RegisterDto dto, HttpSession session) {
        Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");

        if (korisnik != null) {
            return new ResponseEntity("Vec ste prijavljeni", HttpStatus.BAD_REQUEST);
        }

        if (dto.getEmail() == null || dto.getPonovljenEmail() == null || dto.getLozinka() == null || dto.getIme() == null || dto.getKorisnickoIme() == null || dto.getPrezime() == null) {
            return new ResponseEntity("Moraju biti popunjena sva polja", HttpStatus.BAD_REQUEST);
        }

        if (!dto.getEmail().equals(dto.getPonovljenEmail())) {
            return new ResponseEntity("Email se ne poklapa", HttpStatus.BAD_REQUEST);
        }

        if (korisnikService.findByEmail(dto.getEmail()) != null) {
            return new ResponseEntity("Korisnik sa zadatim email-om vec postoji", HttpStatus.BAD_REQUEST);
        }

        korisnikService.registracija(dto);
        return new ResponseEntity<>("Uspesno registrovan", HttpStatus.OK);
    }

    @GetMapping("/{id}/police") // TODO treba da ispise police od prosledjenog korisnika a ne od prijavljenog
    public ResponseEntity listaPolica(@PathVariable Long id, HttpSession session) {
        Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");
        if (korisnik == null) {
            return new ResponseEntity<>("Nisi prijavljen", HttpStatus.FORBIDDEN);
        } else {
            return new ResponseEntity(((Citalac)korisnik).getOstalePolice(), HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}") // TODO obrisati veze
    public ResponseEntity obrisiKorisnika(@PathVariable Long id, HttpSession session) {
        Korisnik korisnik = (Korisnik)session.getAttribute("korisnik");

        if (korisnik == null) {
            return new ResponseEntity("Morate biti prijavljeni", HttpStatus.UNAUTHORIZED);
        }

        if (!korisnikService.findById(id).equals(korisnik.getId()) && !korisnik.getUloga().equals(Uloga.ADMINISTRATOR)) {
            return new ResponseEntity("Samo administrator moze da brise druge korisnike", HttpStatus.FORBIDDEN);
        }

        int rezultat = korisnikService.obrisiKorisnika(id);

        if (rezultat == 1) {
            return new ResponseEntity("Nepostojeci korisnik", HttpStatus.BAD_REQUEST);
        }

        // TODO odaje id administratora!!
        if (rezultat == 2) {
            return new ResponseEntity("Administrator ne moze da se obrise", HttpStatus.FORBIDDEN);
        }

        if (rezultat == 3) {
            return new ResponseEntity("Autor trenutno ne moze da se obrise", HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity("Uspesno obrisan korisnik", HttpStatus.OK);
    }

    @PostMapping("/") //TODO isto kao za registraciju autora
    public ResponseEntity kreirajNalogAutora(@RequestBody RegisterDto dto, HttpSession session) {
        Korisnik korisnik = (Korisnik)session.getAttribute("korisnik");

        if (korisnik == null) {
            return new ResponseEntity("Morate biti prijavljeni", HttpStatus.UNAUTHORIZED);
        }

        if (!korisnik.getUloga().equals(Uloga.ADMINISTRATOR)) {
            return new ResponseEntity("Morate biti administrator", HttpStatus.FORBIDDEN);
        }

        korisnikService.kreirajNalogAutora(dto.getIme(), dto.getPrezime(), dto.getKorisnickoIme(), dto.getEmail(), dto.getLozinka());
        return new ResponseEntity("Uspesno dodan autor", HttpStatus.OK);
    }
}
