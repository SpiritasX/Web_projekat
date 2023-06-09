package com.example.demo.repository;

import com.example.demo.entity.Zanr;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ZanrRepository extends JpaRepository<Zanr, Long> {
    //boolean findByNaziv(String naziv);


    Optional<Zanr> findByNaziv(String naziv);
}
