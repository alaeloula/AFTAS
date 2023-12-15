package com.example.aftas.competition;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
public class CompetitionDtoRes {
    private int code;

    @Future(message = "La date doit être dans le futur")
    @NotNull(message = "La date ne peut pas être nulle")
    private LocalDate date;

    @NotNull(message = "L'heure de début ne peut pas être nulle")
    private LocalTime startTime;

    @NotNull(message = "L'heure de fin ne peut pas être nulle")
    private LocalTime endTime;

    @Min(value = 0, message = "Le nombre de participations doit être au moins 0")
    private int numberOfParticipation;

    @NotBlank(message = "La localisation ne peut pas être vide")
    private String location;

    @Positive(message = "Le montant doit être positif")
    private double amount;
/*
    @OneToMany(mappedBy = "competition", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ranking> rankings;
    @OneToMany(mappedBy = "competition", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Hunting> huntings;*/
}
