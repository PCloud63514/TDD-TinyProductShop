package com.pcloud.member;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class MemberApi {
    private final MemberService memberService;

    @GetMapping("/member/")
    public String hello() {
        return "hello";
    }

    @GetMapping("/member/list")
    public List<Member> getMembers() {
        return memberService.getMembers();
    }

    @PostMapping("/member/join")
    public Long join(@RequestBody MemberJoinDto memberJoinDto) {
        Member joinMember = Member.builder()
                .name(memberJoinDto.getName())
                .address(memberJoinDto.getAddress())
                .build();
        Long id = memberService.saveMember(joinMember);

        return id;
    }
}
