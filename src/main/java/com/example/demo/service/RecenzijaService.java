package com.example.demo.service;

import com.example.demo.entity.Recenzija;
import com.example.demo.repository.RecenzijaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecenzijaService {
    @Autowired
    private RecenzijaRepository recenzijaRepository;
    public Optional<Recenzija> jednaRecenzija(Long id) {
        return recenzijaRepository.findById(id);
    }
}
