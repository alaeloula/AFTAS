package com.example.aftas.level;

import com.example.aftas.fish.Fish;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Level {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int code;

    @NonNull private String description;

    @NonNull private int point;

    @ManyToOne
    @JoinColumn(name = "fish_code")
    private Fish fish;


}
