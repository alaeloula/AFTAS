package com.example.aftas.competition;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface CompetionRepository extends JpaRepository<Competition,Integer> {
    List<CompetitionDtoRes> getCompetitionsByDateBefore(LocalDate date);
}
