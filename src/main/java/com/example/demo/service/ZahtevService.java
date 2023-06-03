package com.example.demo.service;

import com.example.demo.dto.ZahtevDto;
import com.example.demo.entity.*;
import com.example.demo.repository.AutorRepository;
import com.example.demo.repository.KorisnikRepository;
import com.example.demo.repository.ZahtevRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Random;

import static com.example.demo.entity.Status.*;

@Service
public class ZahtevService {
    @Autowired
    private ZahtevRepository zahtevRepository;
    @Autowired
    private KorisnikService korisnikService;
    @Autowired
    private MailService mailService;

    public Zahtev findById(Long id) {
        return zahtevRepository.findById(id).orElse(null);
    }

    public Zahtev save(Zahtev zahtev) {
        return zahtevRepository.save(zahtev);
    }

    public void delete(Zahtev zahtev) {
        zahtevRepository.delete(zahtev);
    }

    public List<Zahtev> findAll() {
        return zahtevRepository.findAll();
    }

    public Zahtev dodajZahtev(String email, String telefon, String poruka, Autor autor) {
        Zahtev zahtev = new Zahtev();
        zahtev.setEmail(email);
        zahtev.setTelefon(telefon);
        zahtev.setDatum(new Date());            // Konstruktor bez parametara postavlja vreme na trenutno
        zahtev.setPoruka(poruka);
        zahtev.setAutor(autor);
        zahtev.setStatus(Status.CEKA);
        zahtev = save(zahtev);
        return zahtev;
    }

    // TODO zahtevi ne mogu da se brisu?
    public Integer obrisiZahtev(Long id) {
        Zahtev zahtev = findById(id);

        if (zahtev == null) {
            return 1;
        }

        delete(zahtev);
        return 0;
    }

    public List<Zahtev> prikaziNepregledaneZahteve() {
        List<Zahtev> nepregledaniZahtevi = zahtevRepository.findByStatus(CEKA);
        return nepregledaniZahtevi;
    }

    public void obradiZahtev(Zahtev zahtev, Boolean prihvati) {
        if (prihvati) {
            String lozinka = generatePassword();
            // TODO moze azuriranje korisnika bolje da se uradi :<
            korisnikService.azurirajAutora(zahtev.getAutor(), zahtev.getEmail(), lozinka);
            posaljiMejlSaLozinkom(zahtev.getEmail(), lozinka);
            zahtev.setStatus(ODOBREN);
        } else {
            posaljiMejlOdbijanje(zahtev.getEmail());
            zahtev.setStatus(ODBIJEN);
        }

        zahtevRepository.save(zahtev);
    }

    private String generatePassword() {
        String validniKarakteri = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*";
        int duzinaLozinke = 10;

        StringBuilder lozinkaBuilder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < duzinaLozinke; i++) {
            int randomIndex = random.nextInt(validniKarakteri.length());
            char slucajniKarakter = validniKarakteri.charAt(randomIndex);
            lozinkaBuilder.append(slucajniKarakter);
        }
        return lozinkaBuilder.toString();
    }

    private void posaljiMejlSaLozinkom(String email, String lozinka) {
        String poruka = "Vaš nalog je uspešno aktiviran. Vaša lozinka je: " + lozinka;
        mailService.posaljiMejl(email, "Aktivacija naloga", poruka);
    }

    private void posaljiMejlOdbijanje(String email) {
        String poruka = "Vaš zahtev za aktivaciju naloga je odbijen.";
        mailService.posaljiMejl(email, "Odbijanje zahteva", poruka);
    }
}
