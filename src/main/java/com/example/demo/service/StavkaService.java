package com.example.demo.service;

import com.example.demo.entity.Stavka;
import com.example.demo.repository.StavkaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StavkaService {

    @Autowired
    private StavkaRepository stavkaRepository;

    public Stavka save(Stavka stavka) {
        return stavkaRepository.save(stavka);
    }

    public void delete(Stavka stavka) {
        stavkaRepository.delete(stavka);
    }
}
