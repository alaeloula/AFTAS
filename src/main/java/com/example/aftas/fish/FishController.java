package com.example.aftas.fish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/fish")
public class FishController {

    private final FishService fishService;

    @Autowired
    public FishController(FishService fishService) {
        this.fishService = fishService;
    }

    @PostMapping
    public ResponseEntity<FishDtoRes> createFish(@RequestBody FishDtoReq fishDto) {
        FishDtoRes createdFish = fishService.createFish(fishDto);
        return new ResponseEntity<>(createdFish, HttpStatus.CREATED);
    }

    @GetMapping("/{fishId}")
    public ResponseEntity<FishDtoRes> getFishById(@PathVariable String fishId) {
        Optional<FishDtoRes> fish = fishService.getFishById(fishId);
        return fish.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<FishDtoRes>> getAllFish() {
        List<FishDtoRes> fishList = fishService.getAllFish();
        return new ResponseEntity<>(fishList, HttpStatus.OK);
    }

    @DeleteMapping("/{fishId}")
    public ResponseEntity<Void> deleteFish(@PathVariable String fishId) {
        fishService.deleteFish(fishId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

