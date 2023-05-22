package com.example.demo.service;

import com.example.demo.dto.LoginDto;
import com.example.demo.dto.RegisterDto;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KorisnikService {

    @Autowired
    private CitalacRepository citalacRepository;

    @Autowired
    private KnjigaRepository knjigaRepository;

    public Citalac login(String korisnickoIme) {
        return citalacRepository.findByKorisnickoIme(korisnickoIme);
    }

    public void register(RegisterDto dto) {
        Citalac citalac = new Citalac();
        citalac.setKorisnickoIme(dto.getKorisnickoIme());
        citalac.setEmail(dto.getEmail());
        citalac.setLozinka(dto.getLozinka());
        citalac.setAdmin(false);
        citalacRepository.save(citalac);
    }

    public List<Citalac> listaCitaoca() {
        return citalacRepository.findAll();
    }

    public List<Korisnik> pretrazi(String type, String str) {
        List<Korisnik> lista = new ArrayList<>();
//        switch (type) {
//            case "korisnici":
//                for (Citalac c : citalacRepository.findByKorisnickoIme(str))
//                    lista.add(c);
//                for (Citalac c : citalacRepository.findByIme(str))
//                    lista.add(c);
//                for (Citalac c : citalacRepository.findByPrezime(str))
//                    lista.add(c);
//                break;
//            case "knjige":
////                for (Knjiga k : knjigaRepository.findByNaslov(str))
////                    lista.add(k);
//        }
        return lista;
    }
}
