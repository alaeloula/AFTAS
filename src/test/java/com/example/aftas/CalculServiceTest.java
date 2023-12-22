package com.example.aftas;


import com.example.aftas.competition.CompetionRepository;
import com.example.aftas.competition.Competition;
import com.example.aftas.exception.ResourceNotFoundException;
import com.example.aftas.ranking.Ranking;
import com.example.aftas.ranking.RankingDtoRes;
import com.example.aftas.ranking.RankingRepository;
import com.example.aftas.ranking.RankingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class CalculServiceTest {

    @Mock
    private CompetionRepository competitionRepository;



    @Mock
    private RankingRepository rankingRepository;

    @InjectMocks
    private RankingService rankingService;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCalculateAndSetRankings() {
        String competitionCode = "yourCompetitionCode";
        Competition competition = new Competition();
        List<RankingDtoRes> expectedRankings = new ArrayList<>();
        when(competitionRepository.findById(competitionCode)).thenReturn(Optional.of(competition));

        List<RankingDtoRes> rankings = rankingService.calculateAndSetRankings(competitionCode);
        int expectedNumberOfTimes = rankings.size();
        verify(rankingRepository, times(expectedNumberOfTimes)).save(any(Ranking.class));
        assertEquals(expectedRankings, rankings);
    }

    @Test
    public void testInvalidCompetitionCode() {
        String competitionCode = "invalidCompetitionCode";
        when(competitionRepository.findById(competitionCode)).thenReturn(Optional.empty());
        verify(rankingRepository, never()).save(any(Ranking.class));
        assertThrows(ResourceNotFoundException.class, () -> rankingService.calculateAndSetRankings(competitionCode));

    }

    @Test
    public void testEmptyHuntingResults() {
        String competitionCode = "emptyHuntingResults";
        Competition competition = new Competition();
        when(competitionRepository.findById(competitionCode)).thenReturn(Optional.of(competition));
        List<RankingDtoRes> rankings = rankingService.calculateAndSetRankings(competitionCode);

        verify(rankingRepository, never()).save(any(Ranking.class));
        assertEquals(0, rankings.size());
    }


}