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
    public ResponseEntity<RankingDtoRes> getRankingById(@PathVariable String competitionCode , @PathVariable  int memberNum) {
        RankingId rankingId = new RankingId(competitionCode , memberNum);
        return rankingService.findById(rankingId)
                .map(ranking -> new ResponseEntity<>(ranking, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Optional<RankingDtoRes>> addRanking(@Valid @RequestBody RankingDtoReq ranking){
        return ResponseEntity.ok(rankingService.save(ranking));
    }

    @DeleteMapping("{competitionid}/{memberid}")
    public ResponseEntity<Void> deleteRankingById(@PathVariable String competitionCode , @PathVariable  int memberNum) {
        RankingId rankingId = new RankingId(competitionCode , memberNum);
        rankingService.deleteById(rankingId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/Competition/{competitionCode}")
    public ResponseEntity<List<RankingDtoRes>> getByCompetition(@PathVariable String competitionCode){
        return ResponseEntity.ok(rankingService.getRankingsByCompetitionCode(competitionCode));
    }

    @PutMapping("/calculate/{id}")
    public ResponseEntity<List<RankingDtoRes>> calculate(@PathVariable String id){
        return ResponseEntity.ok(rankingService.calculateAndSetRankings(id));
    }

    @GetMapping("/podium/{id}")
    public ResponseEntity<List<RankingDtoRes>> podium(@PathVariable String id){
        return ResponseEntity.ok(rankingService.getPodiumByCompetitionCode(id));
    }
}
