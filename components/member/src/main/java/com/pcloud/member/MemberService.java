package com.pcloud.member;

import javassist.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface MemberService {
    Long saveMember(Member member) throws IllegalStateException;
    List<Member> getMembers();
    Member findMemberById(Long memberId) throws NotFoundException;
    void updateMember(Long id, String name, Address address) throws NotFoundException;
}
