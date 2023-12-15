package com.example.aftas.fish;
import java.util.List;
import java.util.Optional;
public interface IFish {
    FishDtoRes createFish(FishDtoReq fish);
    void deleteFish(String fishId);
    Optional<FishDtoRes> getFishById(String fishId);
    List<FishDtoRes> getAllFish();
}
