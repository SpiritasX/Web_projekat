package com.example.demo.service;

import com.example.demo.entity.Knjiga;
import com.example.demo.repository.KnjigaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KnjigaService {
    @Autowired
    private KnjigaRepository knjigaRepository;
    public List<Knjiga> pretrazi(String pretraga) {
        return knjigaRepository.findAllByNaslov(pretraga);
    }
}
