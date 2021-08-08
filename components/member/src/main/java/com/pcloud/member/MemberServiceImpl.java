package com.pcloud.member;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    @Override
    public Long saveMember(MemberJoinForm memberJoinForm) throws IllegalStateException {
        // Name 중복 체크
        List<Member> members = memberRepository.findByName(memberJoinForm.getName());
        if (members.isEmpty() == false) throw new IllegalStateException();

        Member member = Member.builder()
                .name(memberJoinForm.getName())
                .address(memberJoinForm.getAddress())
                .build();

        memberRepository.save(member);

        return member.getId();
    }

    @Override
    public List<Member> getMembers() {
        return memberRepository.findAll();
    }

    @Override
    public Member findMemberById(Long memberId) throws NotFoundException {
        Optional<Member> member = memberRepository.findById(memberId);
        if (member.isEmpty()) throw new NotFoundException("Member does not exist");
        return member.get();
    }

    @Override
    public Member findMemberByName(String name) throws NotFoundException {
        memberRepository.findByName(name);

        return null;
    }

    @Override
    public void updateMember(Long id, String name, Address address) throws NotFoundException {
        Optional<Member> member = memberRepository.findById(id);
        if (member.isEmpty()) throw new NotFoundException("Member does not exist");
        member.get().setName(name);
        member.get().setAddress(address);
    }
}
