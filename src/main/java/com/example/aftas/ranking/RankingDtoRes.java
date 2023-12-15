package com.example.aftas.ranking;

import com.example.aftas.competition.CompetitionDtoRes;
import com.example.aftas.member.MemberDtoRes;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RankingDtoRes {

    private MemberDtoRes member;

    private CompetitionDtoRes competition;

    private int rank;

    private int score;

}
