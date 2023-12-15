package com.example.aftas.member;

import com.example.aftas.hunting.Hunting;
import com.example.aftas.ranking.Ranking;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Membre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int num;

    @NonNull private String name;
    @NonNull private String familyName;
    @NonNull private LocalDate accessionDate;
    @NonNull private String nationality;
    @NonNull private  IdentityDocumentType identityDocument;
    @NonNull private  String identityNumber;
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ranking> rankings;

    @OneToMany(mappedBy = "membre", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Hunting> huntings;

}
