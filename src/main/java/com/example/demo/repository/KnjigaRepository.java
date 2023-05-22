package com.example.demo.repository;

import com.example.demo.entity.Knjiga;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KnjigaRepository extends JpaRepository<Knjiga, Long> {
    List<Knjiga> findAllByNaslov(String pretraga);
}
