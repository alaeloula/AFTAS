package com.example.aftas.ranking;

import java.util.List;
import java.util.Optional;

public interface IRanking {
    List<RankingDtoRes> getAllRankings();
    Optional<RankingDtoRes> findById(RankingId rankingId);
    Optional<RankingDtoRes> save(RankingDtoReq ranking);
    void deleteById(RankingId rankingId);
    boolean existsById(RankingId rankingId);
    long count();
}
