package com.example.aftas.competition;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface CompetionRepository extends JpaRepository<Competition,String> {
    List<Competition> findByDateBefore(LocalDate date);
    Page <Competition> findAll(Pageable pageable);
}
