package com.example.aftas.member;

import com.example.aftas.exception.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
@Validated
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public ResponseEntity<List<MemberDtoRes>> getAllMembers() {
        List<MemberDtoRes> members = memberService.getAllMembers();
        return ResponseEntity.ok(members);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberDtoRes> getMemberById(@PathVariable Integer id) {
        MemberDtoRes member = memberService.getMemberById(id);
        if (member != null) {
            return ResponseEntity.ok(member);
        }
        throw new ResourceNotFoundException("Membre non trouvé avec l'ID : " + id);
    }

    @PostMapping
    public ResponseEntity<MemberDtoRes> createMember(@Valid @RequestBody MemberDtoReq memberCreateDto) {
        MemberDtoRes savedMember = memberService.createMember(memberCreateDto);
        return new ResponseEntity<>(savedMember, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Integer id) {
        memberService.deleteMember(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countMembers() {
        long count = memberService.countMembers();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/exists/{id}")
    public ResponseEntity<Boolean> existsMember(@PathVariable Integer id) {
        boolean exists = memberService.existsMember(id);
        return ResponseEntity.ok(exists);
    }
}
