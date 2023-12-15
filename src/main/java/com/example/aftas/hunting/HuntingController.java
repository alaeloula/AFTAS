package com.example.aftas.hunting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/huntings")
public class HuntingController {

    private final HuntingService huntingService;

    @Autowired
    public HuntingController(HuntingService huntingService) {
        this.huntingService = huntingService;
    }

    @PostMapping
    public ResponseEntity<HuntingDtoRes> createHunting(@RequestBody HuntingDtoReq huntingDto) {
        HuntingDtoRes createdHunting = huntingService.createHunting(huntingDto);
        return new ResponseEntity<>(createdHunting, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHunting(@PathVariable("id") int huntingId) {
        huntingService.deleteHunting(huntingId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HuntingDtoRes> getHuntingById(@PathVariable("id") int huntingId) {
        Optional<HuntingDtoRes> hunting = huntingService.getHuntingById(huntingId);
        return hunting.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<HuntingDtoRes>> getAllHuntings() {
        List<HuntingDtoRes> allHuntings = huntingService.getAllHuntings();
        return new ResponseEntity<>(allHuntings, HttpStatus.OK);
    }


}
