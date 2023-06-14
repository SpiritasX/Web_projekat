package com.example.demo.repository;

import com.example.demo.dto.ZahtevDto;
import com.example.demo.entity.Status;
import com.example.demo.entity.Zahtev;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ZahtevRepository extends JpaRepository<Zahtev, Long> {
    List<Zahtev>findByStatus(Status status);
    //List<Zahtev> findNepregledaniZahtevi();

}
