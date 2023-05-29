package com.example.demo.service;

import com.example.demo.dto.ZahtevDto;
import com.example.demo.entity.Autor;
import com.example.demo.entity.Zahtev;
import com.example.demo.repository.ZahtevRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZahtevService {

    @Autowired
    private ZahtevRepository zahtevRepository;

    public void sacuvajZahtev(ZahtevDto dto, Autor autor) {
        Zahtev zahtev = new Zahtev();
        zahtev.setEmail(dto.getEmail());
        zahtev.setTelefon(dto.getTelefon());
        zahtev.setDatum(dto.getDatum());
        zahtev.setPoruka(dto.getPoruka());
        zahtev.setAutor(autor);
        zahtevRepository.save(zahtev);
    }




}
