package com.example.aftas.ranking;

import com.example.aftas.competition.Competition;
import com.example.aftas.member.Membre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RankingRepository extends JpaRepository<Ranking,RankingId> {
    int countRankingByCompetition(Competition competition);

    List<Ranking> findByCompetition(Competition competition);
    List<Ranking> findByMember(Membre member);


}
