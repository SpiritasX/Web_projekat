package com.example.demo.repository;

import com.example.demo.entity.Knjiga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Repository
public interface KnjigaRepository extends JpaRepository<Knjiga, Long> {
    List<Knjiga> findAllByNaslov(String pretraga);

   // Knjiga findByIsbn(String isbn);
    Optional<Knjiga> findByISBN(String isbn);
}
