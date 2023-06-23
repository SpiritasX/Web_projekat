package com.example.demo.repository;

import com.example.demo.entity.Stavka;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StavkaRepository extends JpaRepository<Stavka, Long> {
    List<Stavka>findAllByKnjigaId(Long knjigaId);
}
