package com.example.aftas.hunting;

import jakarta.validation.constraints.Min;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor

public class HuntingDtoReq {

    @Min(value = 0, message = "numberOfFish must be greater than or equal to 0")
    private int numberOfFish;
    @NonNull
    private int membre_id;
    @NonNull private String competition_id;
    @NonNull private String fish_id;
}
