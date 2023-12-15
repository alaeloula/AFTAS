package com.example.aftas.ranking;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rankings")
public class RankingController {

    private final RankingService rankingService;

    @Autowired
    public RankingController(RankingService rankingService) {
        this.rankingService = rankingService;
    }

    @GetMapping
    public ResponseEntity<List<RankingDtoRes>> getAllRankings() {
        List<RankingDtoRes> rankings = rankingService.getAllRankings();
        return new ResponseEntity<>(rankings, HttpStatus.OK);
    }

    @GetMapping("/{memberid}/{competitionid}")
    public ResponseEntity<RankingDtoRes> getRankingById(@PathVariable int competitionCode , @PathVariable  int memberNum) {
        RankingId rankingId = new RankingId(competitionCode , memberNum);
        return rankingService.findById(rankingId)
                .map(ranking -> new ResponseEntity<>(ranking, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Optional<RankingDtoRes>> addRanking(@Valid @RequestBody RankingDtoReq ranking){
        return ResponseEntity.ok(rankingService.save(ranking));
    }

    @DeleteMapping("/{memberid}/{competitionid}")
    public ResponseEntity<Void> deleteRankingById(@PathVariable int competitionCode , @PathVariable  int memberNum) {
        RankingId rankingId = new RankingId(competitionCode , memberNum);
        rankingService.deleteById(rankingId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Ajoutez d'autres endpoints si nécessaire
}
