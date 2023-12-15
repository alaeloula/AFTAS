package com.example.aftas.member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
public class MemberDtoReq {

    private int num;

    @NonNull
    private String name;
    @NonNull private String familyName;
    @NonNull private LocalDate accessionDate;
    @NonNull private String nationality;
    @NonNull private  IdentityDocumentType identityDocument;
    @NonNull private  String identityNumber;

}
