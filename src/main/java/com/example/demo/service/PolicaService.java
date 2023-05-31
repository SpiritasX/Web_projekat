package com.example.demo.service;

import com.example.demo.dto.RegisterDto;
import com.example.demo.entity.Polica;
import com.example.demo.repository.PolicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PolicaService {


@Autowired
    PolicaRepository policaRepository;

    public Polica dodajPolicu(String po, Boolean primarna){
        Polica polica=new Polica();
        polica.setNaziv(po);
        polica.setPrimarna(primarna);
        policaRepository.save(polica);
        return  polica;
    }

    public List<Polica> listaPolica() {
        return policaRepository.findAll();

    }



}
