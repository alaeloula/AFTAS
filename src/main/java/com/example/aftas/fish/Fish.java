package com.example.aftas.fish;

import com.example.aftas.hunting.Hunting;
import com.example.aftas.level.Level;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Fish {
    @Id
    @NonNull
    private String name;

    @NonNull private double averageWeight;

    @ManyToOne
    @JoinColumn(name = "level_code")
    private Level level;

    @OneToMany(mappedBy = "fish", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Hunting> huntings;
}
