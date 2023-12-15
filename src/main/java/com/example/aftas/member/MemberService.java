package com.example.aftas.member;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<MemberDtoRes> getAllMembers() {
        List<Membre> membres = memberRepository.findAll();
        return membres.stream()
                .map(member -> modelMapper.map(member, MemberDtoRes.class))
                .collect(Collectors.toList());

    }

    public MemberDtoRes getMemberById(Integer memberId) {
        Optional<Membre> optionalMember = memberRepository.findById(memberId);
        return optionalMember.map(member -> modelMapper.map(member, MemberDtoRes.class))
                .orElse(null); // Peut être géré autrement, comme jeter une exception ou retourner un DTO par défaut.
    }


    public MemberDtoRes createMember(MemberDtoReq memberCreateDto) {
        Membre member = modelMapper.map(memberCreateDto, Membre.class);
        Membre savedMember = memberRepository.save(member);
        return modelMapper.map(savedMember, MemberDtoRes.class);
    }


    public void deleteMember(Integer memberId) {
        memberRepository.deleteById(memberId);
    }

    public long countMembers() {
        return memberRepository.count();
    }

    public boolean existsMember(Integer memberId) {
        return memberRepository.existsById(memberId);
    }
}

