package com.example.demo.controller;

import com.example.demo.dto.KorisnikDto;
import com.example.demo.dto.LoginDto;
import com.example.demo.dto.PolicaDto;
import com.example.demo.dto.RegisterDto;
import com.example.demo.entity.Citalac;
import com.example.demo.entity.Korisnik;
import com.example.demo.entity.Autor;
import com.example.demo.entity.Polica;
import com.example.demo.entity.Uloga;
import com.example.demo.service.KorisnikService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/korisnici")
public class KorisnikController {
    @Autowired
    private KorisnikService korisnikService;

    @GetMapping("")
    public ResponseEntity listaKorisnika() {
        List<Korisnik> korisnici = korisnikService.findAll();
        List<KorisnikDto> korisniciDto = new ArrayList<>();
        for (Korisnik k : korisnici) {
            korisniciDto.add(new KorisnikDto(k));
        }
        return new ResponseEntity(korisniciDto, HttpStatus.OK);
    }

    @GetMapping("/prijavljen")
    public ResponseEntity prijavljenKorisnik(HttpSession session) {
        Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");

        if (korisnik == null) {
            return new ResponseEntity("Morate biti prijavljeni", HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity(new KorisnikDto(korisnik), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity jedanKorisnik(@PathVariable Long id) {
        Korisnik korisnik = korisnikService.findById(id);
        if (korisnik != null) {
            return new ResponseEntity(new KorisnikDto(korisnik), HttpStatus.OK);
        }
        return new ResponseEntity("Nepostojeci korisnik", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("prijavi-se")
    public ResponseEntity prijava(@RequestBody LoginDto dto, HttpSession session) {
        System.out.println(session.getId());
        if (dto.getEmail() == null || dto.getLozinka() == null) {
            return new ResponseEntity("Moraju biti popunjena sva polja", HttpStatus.BAD_REQUEST);
        }

        Korisnik korisnik = korisnikService.prijava(dto);

        if (korisnik == null) {
            return new ResponseEntity("Pogresan email ili lozinka", HttpStatus.BAD_REQUEST);
        }

        session.setAttribute("korisnik", korisnik);
        return new ResponseEntity(new KorisnikDto(korisnik, session.getId()), HttpStatus.OK);
    }
    @PostMapping("odjavi-se")
    public ResponseEntity odjava(HttpSession session) {
        session.invalidate();
        return new ResponseEntity("Uspesno odjavljen", HttpStatus.OK);
    }

    @PostMapping("registruj-se")
    public ResponseEntity registracija(@RequestBody RegisterDto dto, HttpSession session) {
        Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");

        if (korisnik != null) {
            return new ResponseEntity("Vec ste prijavljeni", HttpStatus.BAD_REQUEST);
        }

        if (dto.getEmail() == null || dto.getPonovljenaLozinka() == null || dto.getLozinka() == null || dto.getIme() == null || dto.getKorisnickoIme() == null || dto.getPrezime() == null) {
            return new ResponseEntity("Moraju biti popunjena sva polja", HttpStatus.BAD_REQUEST);
        }

        if (!dto.getLozinka().equals(dto.getPonovljenaLozinka())) {
            return new ResponseEntity("Lozinka se ne poklapa. Morate uneti dva puta istu lozinku.", HttpStatus.BAD_REQUEST);
        }

        if (korisnikService.findByEmail(dto.getEmail()) != null) {
            return new ResponseEntity("Korisnik sa zadatim email-om vec postoji", HttpStatus.FORBIDDEN);
        }

        if (korisnikService.findByKorisnickoIme(dto.getKorisnickoIme()) != null) {
            return new ResponseEntity("Korisnik sa zadatim korisnickim imenom vec postoji", HttpStatus.FORBIDDEN);
        }

        korisnikService.registracija(dto.getIme(), dto.getPrezime(), dto.getKorisnickoIme(), dto.getEmail(), dto.getLozinka(), Uloga.CITALAC, true);

        return new ResponseEntity<>("Uspesno registrovan", HttpStatus.OK);
    }

    @GetMapping("/{id}/police")
    public ResponseEntity listaPolica(@PathVariable Long id) {
        Korisnik korisnik = korisnikService.findById(id);

        if (korisnik == null) {
            return new ResponseEntity("Nepostojeci korisnik", HttpStatus.BAD_REQUEST);
        }

        Set<PolicaDto> police = new HashSet<>();
        for (Polica p : ((Citalac)korisnikService.findById(korisnik.getId())).getOstalePolice()) {
            police.add(new PolicaDto(p));
        }

        return new ResponseEntity(police, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity obrisiKorisnika(@PathVariable Long id, HttpSession session) {
        Korisnik korisnik = (Korisnik)session.getAttribute("korisnik");

        if (korisnik == null) {
            return new ResponseEntity("Morate biti prijavljeni", HttpStatus.UNAUTHORIZED);
        }

        if (!korisnikService.findById(id).equals(korisnik.getId()) && !korisnik.getUloga().equals(Uloga.ADMINISTRATOR)) {
            return new ResponseEntity("Samo administrator moze da brise druge korisnike", HttpStatus.FORBIDDEN);
        }

        int kodGreske = korisnikService.obrisiKorisnika(id);

        switch (kodGreske) {
            case 1:
                return new ResponseEntity("Nepostojeci korisnik", HttpStatus.BAD_REQUEST);
            case 2:
                // TODO odaje id administratora!!
                return new ResponseEntity("Administrator ne moze da se obrise", HttpStatus.FORBIDDEN);
            case 3:
                return new ResponseEntity("Autor trenutno ne moze da se obrise", HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity("Uspesno obrisan korisnik", HttpStatus.OK);
    }

    @PostMapping("/kreiraj-autora")
    public ResponseEntity kreirajNalogAutora(@RequestBody RegisterDto dto, HttpSession session) {
        Korisnik korisnik = (Korisnik)session.getAttribute("korisnik");

        if (korisnik == null) {
            return new ResponseEntity("Morate biti prijavljeni", HttpStatus.UNAUTHORIZED);
        }

        if (!korisnik.getUloga().equals(Uloga.ADMINISTRATOR)) {
            return new ResponseEntity("Morate biti administrator", HttpStatus.FORBIDDEN);
        }
        if(korisnikService.findByKorisnickoIme(dto.getKorisnickoIme() )!= null){
            return new ResponseEntity<>("morate uneti unique korisnika",HttpStatus.BAD_REQUEST);
        }
        if(korisnikService.findByEmail(dto.getEmail() )!= null){
            return new ResponseEntity<>("morate uneti unique korisnika",HttpStatus.BAD_REQUEST);
        }

        korisnikService.registracija(dto.getIme(), dto.getPrezime(), dto.getKorisnickoIme(), dto.getEmail(), dto.getLozinka(), Uloga.AUTOR, false);

        return new ResponseEntity("Uspesno dodan autor", HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity azurirajKorisnika(@RequestBody KorisnikDto dto, HttpSession session) {
        Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");

        if (korisnik == null) {
            return new ResponseEntity("Morate biti prijaljeni", HttpStatus.UNAUTHORIZED);
        }

        if (dto.getEmail() != null && korisnikService.findByEmail(dto.getEmail()) != null) {
            return new ResponseEntity("Vec postoji taj email", HttpStatus.FORBIDDEN);
        }

        korisnikService.azurirajKorisnika(korisnik, dto);
        return new ResponseEntity("Uspesno azurirano", HttpStatus.OK);
    }
}
