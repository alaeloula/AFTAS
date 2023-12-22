package com.example.aftas.ranking;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RankingDtoReq {

    @NonNull private int memberId;

    @NonNull private String competitionId;

    private int rank;

    private int score;

}
