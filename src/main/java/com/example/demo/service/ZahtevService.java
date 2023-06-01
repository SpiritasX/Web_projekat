package com.example.demo.service;

import com.example.demo.dto.ZahtevDto;
import com.example.demo.entity.Autor;
import com.example.demo.entity.Status;
import com.example.demo.entity.Zahtev;
import com.example.demo.repository.AutorRepository;
import com.example.demo.repository.ZahtevRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import static com.example.demo.entity.Status.*;

@Service
public class ZahtevService {

    @Autowired
    private ZahtevRepository zahtevRepository;
    @Autowired
    private AutorRepository autorRepository;

    public void sacuvajZahtev(ZahtevDto dto, Autor autor) {
        Zahtev zahtev = new Zahtev();
        zahtev.setEmail(dto.getEmail());
        zahtev.setTelefon(dto.getTelefon());
        zahtev.setDatum(dto.getDatum());
        zahtev.setPoruka(dto.getPoruka());
        zahtev.setAutor(autor);
        zahtev.setStatus(Status.CEKA);
        zahtev = zahtevRepository.save(zahtev);
    }
    public List<Zahtev>prikaziNepregledaneZahteve() {
        List<Zahtev> nepregledaniZahtevi = zahtevRepository.findByStatus(CEKA);
        return nepregledaniZahtevi;
    }
    public void obradiZahtev(Zahtev zahtev, String status) {
       // Zahtev zahtev;
        if (status.equals(ODOBREN)) {
            // Kreiranje naloga autora, generisanje lozinke, slanje mejla sa lozinkom, itd.
            String lozinka = generatePassword();
            kreirajNalogAutora(zahtev, lozinka);
            posaljiMejlSaLozinkom(zahtev.getEmail(), lozinka);
        } else if (status.equals(ODBIJEN)) {
            posaljiMejlOdbijanje(zahtev.getEmail());
        }
        //zahtev.setStatus(status);
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
        // bilo bi dobro da napravimo neki mailService ali ne znam kako
       // mailService.posaljiMejl(email, "Aktivacija naloga", poruka);
    }

    private void posaljiMejlOdbijanje(String email) {

        String poruka = "Vaš zahtev za aktivaciju naloga je odbijen.";
        //mailService.posaljiMejl(email, "Odbijanje zahteva", poruka);
    }
    public void kreirajNalogAutora(Zahtev zahtev, String lozinka) {
        Autor autor = new Autor();
        autor=zahtev.getAutor();
        autor.setEmail(zahtev.getEmail());
        autor.setLozinka(lozinka);


        autorRepository.save(autor);
    }



}
