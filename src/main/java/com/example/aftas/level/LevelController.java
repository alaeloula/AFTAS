package com.example.aftas.level;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/levels")
public class LevelController {

    private final LevelService levelService;

    @Autowired
    public LevelController(LevelService levelService) {
        this.levelService = levelService;
    }

    @PostMapping
    public ResponseEntity<Level> createLevel(@RequestBody LevelDto levelDto) {
        try {
            Level createdLevel = levelService.createLevel(levelDto);
            return new ResponseEntity<>(createdLevel, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{levelId}")
    public ResponseEntity<Level> getLevelById(@PathVariable int levelId) {
        Optional<Level> level = levelService.getLevelById(levelId);
        return level.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Level>> getAllLevels() {
        List<Level> levels = levelService.getAllLevels();
        return new ResponseEntity<>(levels, HttpStatus.OK);
    }

    @DeleteMapping("/{levelId}")
    public ResponseEntity<Void> deleteLevel(@PathVariable int levelId) {
        levelService.deleteLevel(levelId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
