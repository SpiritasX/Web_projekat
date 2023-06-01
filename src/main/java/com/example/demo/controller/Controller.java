package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.entity.*;
import com.example.demo.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class Controller {
    @Autowired
    private ZanrService zanrService;
    @Autowired
    private KorisnikService korisnikService;
    @Autowired
    private PolicaService policaService;
    @Autowired
    private AutorService autorService;
    @Autowired
    private ZahtevService zahtevService;
    @Autowired
    private RecenzijaService recenzijaService;
    @Autowired
    private KnjigaService knjigaService;

    @GetMapping("/")
    private String home() {
        return "Hello, World!"; //"<form action=\"/api/login\" method=\"POST\"><button name=\"korisnickoIme\" value=\"Login\"/></form>";
    }

    @PostMapping("/api/prijavi-se")
    public ResponseEntity prijava(@RequestBody LoginDto dto, HttpSession session) {
        Citalac citalac = korisnikService.prijava(dto.getEmail());
        if (citalac == null)
            return new ResponseEntity("Pogresan email.", HttpStatus.BAD_REQUEST);
        else if (citalac.getLozinka().equals(dto.getLozinka())) {
            session.setAttribute("citalac", citalac);

            String citalacString = citalac.toString();
            String redirectUrl = UriComponentsBuilder.fromPath("/api/prijavljen")
                    .queryParam("citalacString", citalacString)
                    .toUriString();

            return ResponseEntity.status(HttpStatus.FOUND)
                    .header("Location", redirectUrl)
                    .build();
        }
        else
            return new ResponseEntity("Pogresna lozinka", HttpStatus.BAD_REQUEST);
    }/*
@PostMapping("/api/prijavi-se")
public RedirectView prijava(@RequestBody LoginDto dto, HttpSession session, RedirectAttributes redirectAttributes) {
    Citalac citalac = korisnikService.prijava(dto.getEmail());

    if (citalac == null) {
        // Redirect to an error page or handle the error accordingly
        return new RedirectView("/error-page");
    } else if (citalac.getLozinka().equals(dto.getLozinka())) {
        session.setAttribute("citalac", citalac);

        // Add citalac.toString() as a flash attribute
        redirectAttributes.addFlashAttribute("citalacString", citalac.toString());

        // Redirect to the "/api/prijavljen" link
        return new RedirectView("/api/prijavljen");
    } else {
        // Redirect to an error page or handle the error accordingly
        return new RedirectView("/error-page");
    }
}*/
    @GetMapping("/api/prijavljen")
    public ResponseEntity listaPolica(HttpSession session) {
        Citalac citalac = (Citalac) session.getAttribute("citalac");
        if (citalac == null) {
            return new ResponseEntity<>("Nisi prijavljen", HttpStatus.FORBIDDEN);
        }
        else {
            return new ResponseEntity(citalac.getOstalePolice(), HttpStatus.OK);
        }
    }

//    curl http://localhost:8880/api/register -d '{"korisnickoIme":"test","email":"test@test.test","lozinka":"test123"}'
//    TODO ponovljena email adresa i mora da bude jedinstvena, kao i korisnicko ime
    @PostMapping("/api/registruj-se")
    public ResponseEntity registracija(@RequestBody RegisterDto dto, HttpSession session) {
        Citalac citalac = (Citalac) session.getAttribute("citalac");
        if (citalac == null) {
            korisnikService.registracija(dto);
            return new ResponseEntity<>("Uspesno registrovan", HttpStatus.OK);
        }
        else {
            return new ResponseEntity("vec ste prijavljeni", HttpStatus.BAD_REQUEST);
        }
    }

    //     curl http://localhost:8880/api/pretrazi?pretraga=test
    @PostMapping("/api/pretrazi")
    public String pretrazi(@RequestParam String pretraga) {
       // return knjigaService.pretrazi(pretraga).toString();
        return "neki";
        // TODO bolji algoritam pretrazivanja
    }

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
        Optional<Zanr> zanr = zanrService.jedanZanr(id);
        if (!zanr.isPresent())
            return new ResponseEntity("Nepostojeci zanr.", HttpStatus.BAD_REQUEST);
        else
            return new ResponseEntity(zanr.get(), HttpStatus.OK);
    }

    @GetMapping("/api/recenzije/{id}")
    public ResponseEntity jednaRecenzija(@PathVariable Long id) {
        Optional<Recenzija> recenzija = recenzijaService.jednaRecenzija(id);
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
    public ResponseEntity podnesiZahtev(@RequestBody ZahtevDto dto) {
        Autor autor = autorService.pronadjiAutora(dto.getImeAutora());
        zahtevService.sacuvajZahtev(dto,autor);
        return new ResponseEntity<>("Uspesno dodan", HttpStatus.OK);
    }

//        Zahtev se salje stiskom na taster SA profila autora sto znaci da
//        je autor vec kreiran i da ce ime uvek biti tacno i postojece!
//        if (autor == null)
//            return new ResponseEntity("Nepostojeci autor.", HttpStatus.BAD_REQUEST);



////////////////////////////////////////// CITALAC //////////////////////////////////////////



















/////////////////////////AUTOR///////////////////////
@PostMapping("/api/dodaj-novu-knjigu")
public ResponseEntity dodajNovuKnjigu(@RequestBody String nazivKnjige, String isbn, Date datum, Integer str, String slika, HttpSession session){
    Autor autor=(Autor) session.getAttribute("autor");
    if(autor==null){
        return new ResponseEntity("nepostojeci autor",HttpStatus.FORBIDDEN);
    }
    else{
            Knjiga knjiga;
            knjiga=knjigaService.dodajKnjigu(nazivKnjige,slika,isbn,datum,str);
            autorService.dodajKnjigu(knjiga,autor);
        return  new ResponseEntity<>("Uspesno dodata nova knjiga",HttpStatus.OK);
    }

}
//public ResponseEntity azurirajPostojecuKnjigu(@RequestBody )
///////////////////////////ADMINISTRATOR///////////////
    @PostMapping ("/api/dodaj-zanr")
    public ResponseEntity dodajNoviZanr(@RequestBody String naziv, HttpSession session){
        Korisnik korisnik= (Korisnik) session.getAttribute("korisnik");
        if(!korisnik.getAdmin()){
            return new ResponseEntity<>("morate biti admin da biste dodavali zanrove",HttpStatus.FORBIDDEN);
        } else{
            Zanr zanr;
            zanr=zanrService.dodajZanr(naziv);
            return new ResponseEntity<>("Uspesno dodat zanr", HttpStatus.OK);
        }
    }
    //korisnik preimenujem u citalac
}

