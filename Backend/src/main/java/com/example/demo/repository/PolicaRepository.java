package com.example.demo.repository;

import com.example.demo.entity.Polica;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface PolicaRepository extends JpaRepository<Polica, Long> {

    Optional<Polica> findById(Long id);
    Optional<Polica>findByNaziv(String naziv);
}
