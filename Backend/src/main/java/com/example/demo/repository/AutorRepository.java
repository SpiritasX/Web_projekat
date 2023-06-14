package com.example.demo.repository;

import com.example.demo.entity.Autor;
import com.example.demo.entity.Korisnik;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {
}
