package com.example.demo.repository;

import com.example.demo.entity.Polica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PolicaRepository extends JpaRepository<Polica, Long> {

    Optional<Polica> findById(Long id);
}
