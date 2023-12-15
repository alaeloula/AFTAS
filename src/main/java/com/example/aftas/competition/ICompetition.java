package com.example.aftas.competition;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ICompetition {
    CompetitionDtoRes save(CompetitionDtoReq competition);

    void deleteById(Integer competitionId);

    Optional<CompetitionDtoRes> findById(Integer competitionId);

    List<CompetitionDtoRes> getAllCompetitions();

    boolean existsById(Integer competitionId);

    long count();

    List<CompetitionDtoRes> getCompetitionsByDateBefore(LocalDate date);
}
