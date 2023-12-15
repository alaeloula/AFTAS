package com.example.aftas.level;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class LevelDto {
    private int code;

    @NonNull
    private String description;

    @NonNull private int point;
}
