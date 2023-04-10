package com.example.demo.repository;

import com.example.demo.entity.Zahtev;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ZahtevRepository extends JpaRepository<Zahtev, Long> {
}
