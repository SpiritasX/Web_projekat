package com.example.demo.repository;

import com.example.demo.entity.Korisnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface KorisnikRepository extends JpaRepository<Korisnik, Long> {

}
