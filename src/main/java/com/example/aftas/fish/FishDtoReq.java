package com.example.aftas.fish;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FishDtoReq {

    @NonNull
    private String name;

    @NonNull private double averageWeight;

    @Min(value = 0, message = "Level ID must be greater than or equal to 0")

    private int level_id;
}
