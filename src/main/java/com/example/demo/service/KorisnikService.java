package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class KorisnikService {


    @Autowired
    private CitalacRepository citalacRepository;

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
        citalacRepository.save(citalac);
    }


    public List<Citalac> listaCitaoca() {
        return citalacRepository.findAll();
    }

    public Optional<Citalac> jedanCitalac(Long id) {
        return citalacRepository.findById(id);
    }
}








