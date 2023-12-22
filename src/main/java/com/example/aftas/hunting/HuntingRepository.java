package com.example.aftas.hunting;

import com.example.aftas.competition.Competition;
import com.example.aftas.fish.Fish;
import com.example.aftas.member.Membre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HuntingRepository extends JpaRepository<Hunting,Integer> {

    List<Hunting> findByCompetitionAndMembre(Competition competition, Membre member);
    Optional<Hunting> findByCompetitionAndFishAndMembre(Competition Competition, Fish fish, Membre member);
}
