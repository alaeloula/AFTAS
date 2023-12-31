package com.example.aftas.competition;


import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/competitions")
public class CompetitionController {

    private final CompetitionService competitionService;
    private static final Logger logger =  LoggerFactory.getLogger(CompetitionController.class);

    @Autowired
    public CompetitionController(CompetitionService competitionService) {
        this.competitionService = competitionService;
    }

   // @GetMapping
//    public ResponseEntity<List<CompetitionDtoRes>> getAllCompetitions() {
//        List<CompetitionDtoRes> competitions = competitionService.getAllCompetitions();
//        return new ResponseEntity<>(competitions, HttpStatus.OK);
//    }
    @GetMapping
    public ResponseEntity<Page<CompetitionDtoRes>> getAllCompetitions(Pageable pageable) {
        Page<CompetitionDtoRes> competitions = competitionService.getAllCompetitions(pageable);
        return ResponseEntity.ok().body(competitions);
    }



    @GetMapping("/{id}")
    public ResponseEntity<CompetitionDtoRes> getCompetitionById(@PathVariable("id") String competitionId) {
        return competitionService.findById(competitionId)
                .map(competition -> new ResponseEntity<>(competition, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<CompetitionDtoRes> createCompetition(@Valid @RequestBody CompetitionDtoReq competitionDtoReq) {
        logger.info("Received competition data for creation: {}"+ competitionDtoReq);
        CompetitionDtoRes createdCompetition = competitionService.save(competitionDtoReq);
        return new ResponseEntity<>(createdCompetition, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompetitionById(@PathVariable("id") String competitionId) {
        competitionService.deleteById(competitionId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Ajoutez d'autres endpoints si nécessaire
    @GetMapping("/before")
    public List<CompetitionDtoRes> getCompetitionsBeforeToday() {
        return competitionService.getCompetitionsBeforeToday();
    }
}

