package com.example.demo.repository;

import com.example.demo.entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends KorisnikRepository {
    Autor findByIme(String ime);
}
