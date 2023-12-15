package com.example.aftas.fish;

import com.example.aftas.level.LevelDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FishDtoRes {


    private String name;

    @NonNull private double averageWeight;

    @NonNull private LevelDto level;


}
