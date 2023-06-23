package com.example.demo.controller;

import com.example.demo.dto.KnjigaDto;
import com.example.demo.entity.Knjiga;
import com.example.demo.entity.Korisnik;
import com.example.demo.entity.Uloga;
import com.example.demo.service.KnjigaService;
import com.example.demo.service.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.dto.KorisnikDto;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {
    @Autowired
    private KorisnikService korisnikService;
    @Autowired
    private KnjigaService knjigaService;

    @GetMapping("/")
    private ResponseEntity home() {
        return new ResponseEntity("Hello, World!", HttpStatus.OK); //"<form action=\"/api/login\" method=\"POST\"><button name=\"korisnickoIme\" value=\"Login\"/></form>";
    }

    // TODO ima duplikata zato sto se uvek prolazi kroz ceo niz
    @GetMapping("/api/pretrazi")
    public ResponseEntity pretrazi(@RequestParam String pretraga, @RequestParam(required = false) String grupa) {
        switch (grupa) {
            case "korisnici":
//                List<Korisnik> korisnici = korisnikService.findAll();
//                List<KorisnikDto> korisniciDto = new ArrayList<>();
////                for (Korisnik k : korisnici) {
////                    if (!k.getUloga().equals(Uloga.CITALAC)) {
////                        korisnici.remove(k);
////                    }
////                }
//                for (Korisnik k : korisnici) {
//                    if (k.getIme().toLowerCase().equals(pretraga.toLowerCase())) {
//                        korisniciDto.add(new KorisnikDto(k));
//                    }
//                }
//                for (Korisnik k : korisnici) {
//                    if (k.getPrezime().toLowerCase().equals(pretraga.toLowerCase())) {
//                        korisniciDto.add(new KorisnikDto(k));
//                    }
//                }
//                for (Korisnik k : korisnici) {
//                    if (k.getIme().toLowerCase().contains(pretraga.toLowerCase())) {
//                        korisniciDto.add(new KorisnikDto(k));
//                    }
//                }
//                for (Korisnik k : korisnici) {
//                    if (k.getPrezime().toLowerCase().contains(pretraga.toLowerCase())) {
//                        korisniciDto.add(new KorisnikDto(k));
//                    }
//                }
//                for (Korisnik k : korisnici) {
//                    korisniciDto.add(new KorisnikDto(k));
//                }
//                return new ResponseEntity(korisniciDto, HttpStatus.OK);
            case "autori":
//                List<Korisnik> autori = korisnikService.findAll();
//                List<KorisnikDto> autoriDto = new ArrayList<>();
////                for (Korisnik k : autori) {
////                    if (!k.getUloga().equals(Uloga.AUTOR)) {
////                        autori.remove(k);
////                    }
////                }
//                for (Korisnik k : autori) {
//                    if (k.getIme().toLowerCase().equals(pretraga.toLowerCase())) {
//                        autoriDto.add(new KorisnikDto(k));
//                    }
//                }
//                for (Korisnik k : autori) {
//                    if (k.getPrezime().toLowerCase().equals(pretraga.toLowerCase())) {
//                        autoriDto.add(new KorisnikDto(k));
//                    }
//                }
//                for (Korisnik k : autori) {
//                    if (k.getIme().toLowerCase().contains(pretraga.toLowerCase())) {
//                        autoriDto.add(new KorisnikDto(k));
//                    }
//                }
//                for (Korisnik k : autori) {
//                    if (k.getPrezime().toLowerCase().contains(pretraga.toLowerCase())) {
//                        autoriDto.add(new KorisnikDto(k));
//                    }
//                }
//                for (Korisnik k : autori) {
//                    autoriDto.add(new KorisnikDto(k));
//                }
//                return new ResponseEntity(autoriDto, HttpStatus.OK);
            default: // case "knjige":
                List<Knjiga> knjige = knjigaService.findAll();
                List<KnjigaDto> knjigeDto = new ArrayList<>();
                for (Knjiga k : knjige) {
                    if (k.getNaslov().toLowerCase().equals(pretraga.toLowerCase())) {
                        knjigeDto.add(new KnjigaDto(k));
                    }
                }
                for (Knjiga k : knjige) {
                    if (k.getNaslov().toLowerCase().contains(pretraga.toLowerCase())) {
                        knjigeDto.add(new KnjigaDto(k));
                    }
                }
                for (Knjiga k : knjige) {
                    if (k.getISBN().equals(pretraga)) {
                        knjigeDto.add(new KnjigaDto(k));
                    }
                }
                for (Knjiga k : knjige) {
                    knjigeDto.add(new KnjigaDto(k));
                }
                return new ResponseEntity(knjigeDto, HttpStatus.OK);
        }
    }
}

