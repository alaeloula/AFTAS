package com.example.aftas.member;

import java.util.List;

public interface IMembre {
    List<MemberDtoRes> getAllMembers();
    MemberDtoRes getMemberById(Integer memberId);
    MemberDtoRes createMember(MemberDtoReq memberCreateDto);

    void deleteMember(Integer memberId);
    long countMembers();
}
