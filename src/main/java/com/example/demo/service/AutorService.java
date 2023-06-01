package com.example.demo.service;

import com.example.demo.entity.Autor;
import com.example.demo.entity.Knjiga;
import com.example.demo.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
public class AutorService {
    @Autowired
    private AutorRepository autorRepository;
    public Autor pronadjiAutora(String ime) {
        return autorRepository.findByIme(ime);
    }



}
