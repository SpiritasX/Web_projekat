package com.example.demo.service;

import com.example.demo.dto.RegisterDto;
import com.example.demo.entity.Polica;
import com.example.demo.repository.PolicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PolicaService {


@Autowired
    PolicaRepository policaRepository;

    public Polica dodajPolicu(String po){
        Polica polica=new Polica();
        polica.setNaziv(po);
        polica.setPrimarna(true);
        policaRepository.save(polica);
        return  polica;
    }
    public List<Polica> listaPolica() {
        return policaRepository.findAll();
        //return policaRepository.findById(id);
    }
}
