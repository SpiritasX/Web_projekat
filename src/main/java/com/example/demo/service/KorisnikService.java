package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class KorisnikService {


    @Autowired
    private CitalacRepository citalacRepository;
    @Autowired
    private PolicaService policaService;

    public Citalac prijava(String email) {
        return citalacRepository.findByEmail(email);
    }

    public void registracija(RegisterDto dto) {
        Citalac citalac = new Citalac();


        citalac.setIme(dto.getIme());
        citalac.setPrezime(dto.getPrezime());
        citalac.setKorisnickoIme(dto.getKorisnickoIme());
        citalac.setEmail(dto.getEmail());
        citalac.setLozinka(dto.getLozinka());
        citalac.setAdmin(false);

        Set<Polica> police = new HashSet<Polica>();
        police.add(policaService.dodajPolicu("Read"));
        police.add(policaService.dodajPolicu("Currently Reading"));
        police.add(policaService.dodajPolicu("Want To Read"));
        citalac.setOstalePolice(police);
        citalacRepository.save(citalac);
    }


    public List<Citalac> listaCitaoca() {
        return citalacRepository.findAll();
    }

    public Optional<Citalac> jedanCitalac(Long id) {
        return citalacRepository.findById(id);
    }


}








