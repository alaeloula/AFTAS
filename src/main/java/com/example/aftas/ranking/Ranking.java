package com.example.aftas.ranking;

import com.example.aftas.competition.Competition;
import com.example.aftas.member.Membre;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.*;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ranking {
 @EmbeddedId
 private RankingId id;

 @ManyToOne
 @MapsId("competitionCode")
 private Competition competition;

 @ManyToOne
 @MapsId("memberNum")
 private Membre member;

 private int rank;
 private int score;

}



