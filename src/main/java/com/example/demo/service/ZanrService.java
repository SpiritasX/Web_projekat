package com.example.demo.service;

import com.example.demo.entity.Zanr;
import com.example.demo.repository.ZanrRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ZanrService {
    private ZanrRepository zanrRepository;
    public Optional<Zanr> jedanZanr(Long id) {
        return zanrRepository.findById(id);
    }
}
