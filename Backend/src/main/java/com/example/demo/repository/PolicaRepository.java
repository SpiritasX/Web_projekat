package com.example.demo.repository;

import com.example.demo.entity.Polica;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PolicaRepository extends JpaRepository<Polica, Long> {

    Optional<Polica> findById(Long id);
    List<Polica>findByNaziv(String naziv);
}
