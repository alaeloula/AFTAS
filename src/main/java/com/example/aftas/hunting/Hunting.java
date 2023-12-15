package com.example.aftas.hunting;

import com.example.aftas.competition.Competition;
import com.example.aftas.fish.Fish;
import com.example.aftas.member.Membre;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Hunting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Min(value = 0, message = "numberOfFish must be greater than or equal to 0")
    private Integer numberOfFish;

    @ManyToOne(fetch = FetchType.LAZY)
    @NonNull private Membre membre;

    @ManyToOne(fetch = FetchType.LAZY)
    @NonNull private Competition competition;
    @ManyToOne(fetch = FetchType.LAZY)
    @NonNull private Fish fish;


}
