package com.example.aftas.competition;

import java.util.List;
import java.util.Optional;

public interface ICompetition {
    CompetitionDtoRes save(CompetitionDtoReq competition);

    void deleteById(String competitionId);

    Optional<CompetitionDtoRes> findById(String competitionId);

    //List<CompetitionDtoRes> getAllCompetitions();

    boolean existsById(Integer competitionId);

    long count();

    List<CompetitionDtoRes> getCompetitionsBeforeToday();
}
