package com.example.aftas.member;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@RequiredArgsConstructor

public class MemberDtoRes {

    private int num;

    @NonNull
    private String name;
    @NonNull private String familyName;
    @NonNull private LocalDate accessionDate;
    @NonNull private String nationality;
    @NonNull private  IdentityDocumentType identityDocument;
    @NonNull private  String identityNumber;
}
