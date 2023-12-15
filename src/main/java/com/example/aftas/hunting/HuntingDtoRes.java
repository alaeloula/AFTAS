package com.example.aftas.hunting;

import com.example.aftas.competition.CompetitionDtoRes;
import com.example.aftas.fish.FishDtoRes;
import com.example.aftas.member.MemberDtoRes;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class HuntingDtoRes {

    private int id;

    private int numberOfFish;
    private MemberDtoRes membre;
    private CompetitionDtoRes competition;
    private FishDtoRes fish;
}
