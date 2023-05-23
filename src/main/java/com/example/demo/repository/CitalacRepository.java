package com.example.demo.repository;

import com.example.demo.entity.Citalac;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CitalacRepository extends JpaRepository<Citalac, Long> {
    Citalac findByEmail(String email);

}
