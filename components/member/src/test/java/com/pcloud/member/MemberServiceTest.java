package com.pcloud.member;

import javassist.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    MemberService memberService;
    SpyMemberRepository memberRepository;

    @BeforeEach
    void setUp() {
        memberRepository = new SpyMemberRepository();
        memberService = new MemberServiceImpl(memberRepository);
    }

    @Test
    void saveMember_callsSaveInMemberRepository() throws IllegalStateException {
        MemberJoinForm givenMemberJoinForm = new MemberJoinForm("name", new Address());

        Long id = memberService.saveMember(givenMemberJoinForm);

        assertThat(memberRepository.save_wasCalled).isTrue();
        assertThat(id).isEqualTo(memberRepository.save_argumentMember.getId());
    }

    @Test
    void saveMember_throwException_whenExistsName() {
        Member givenMember = Member.builder()
                .name("name")
                .build();
        MemberJoinForm givenMemberJoinForm = new MemberJoinForm("name", new Address());

        memberRepository.findByName_returnValue = List.of(givenMember);

        assertThrows(IllegalStateException.class, () -> {
           memberService.saveMember(givenMemberJoinForm);
        });
    }

    @Test
    void getMembers_callFindAllInMemberRepository() {
        List<Member> members = memberService.getMembers();

        assertThat(memberRepository.findAll_wasCalled).isTrue();
        assertThat(memberRepository.defaultMembers).isEqualTo(members);
    }

    @Test
    void findMemberById_callFindByIdInMemberRepository() throws NotFoundException {
        Member givenMember = Member.builder()
                .build();
        memberRepository.findById_member = givenMember;
        Member member = memberService.findMemberById(givenMember.getId());

        assertThat(memberRepository.findById_wasCalled).isTrue();
        assertThat(member.getId()).isEqualTo(givenMember.getId());
    }

    @Test
    void findMemberById_throwException_ExistId() {
        Member givenMember = Member.builder()
                .build();

        assertThrows(NotFoundException.class, () -> {
           memberService.findMemberById(givenMember.getId());
        });
    }

    @Test
    void updateMember_callFindByIdInMemberRepository() throws NotFoundException {
        Address givenAddress = Address.builder()
                .city("city")
                .street("street")
                .zipcode("zipcode")
                .build();
        Member givenMember = Member.builder()
                .name("name")
                .address(givenAddress)
                .build();
        Address update_givenAddress = Address.builder()
                .city("update_city")
                .street("update_street")
                .zipcode("update_zipcode")
                .build();

        memberRepository.findById_member = givenMember;
        memberService.updateMember(givenMember.getId(), "update_name", update_givenAddress);

        assertThat(memberRepository.findById_wasCalled).isTrue();
        assertThat(memberRepository.findById_member.getName()).isEqualTo("update_name");
        assertThat(memberRepository.findById_member.getAddress()).isEqualTo(update_givenAddress);
    }

    @Test
    void updateMember_throwException_ExistId() {
        Member givenMember = Member.builder()
                .build();
        Address givenAddress = Address.builder()
                .build();
        assertThrows(NotFoundException.class, () -> {
            memberService.updateMember(givenMember.getId(), "update_name", givenAddress);
        });
    }

    @Test
    void findMemberByName_callInMemberRepository() throws NotFoundException {
        memberService.findMemberByName("name");
        assertThat(memberRepository.findByName_wasCalled).isTrue();
    }

    private static class SpyMemberRepository implements MemberRepository {
        private List<Member> defaultMembers = Collections.emptyList();
        // save
        public boolean save_wasCalled = false;
        public Member save_argumentMember;

        public boolean findAll_wasCalled = false;

        public List<Member> findByName_returnValue = defaultMembers;

        private boolean findById_wasCalled = false;
        public Member findById_member;

        private boolean findByName_wasCalled = false;

        @Override
        public List<Member> findAll() {
            findAll_wasCalled = true;
            return defaultMembers;
        }

        @Override
        public List<Member> findAll(Sort sort) {
            return null;
        }

        @Override
        public Page<Member> findAll(Pageable pageable) {
            return null;
        }

        @Override
        public List<Member> findAllById(Iterable<Long> longs) {
            return null;
        }

        @Override
        public long count() {
            return 0;
        }

        @Override
        public void deleteById(Long aLong) {

        }

        @Override
        public void delete(Member entity) {

        }

        @Override
        public void deleteAll(Iterable<? extends Member> entities) {

        }

        @Override
        public void deleteAll() {

        }

        @Override
        public <S extends Member> S save(S entity) {
            save_wasCalled = true;
            save_argumentMember = entity;
            return entity;
        }

        @Override
        public <S extends Member> List<S> saveAll(Iterable<S> entities) {
            return null;
        }

        @Override
        public Optional<Member> findById(Long aLong) {
            findById_wasCalled = true;
            if (findById_member == null)
                return Optional.empty();
            return Optional.of(findById_member);
        }

        @Override
        public boolean existsById(Long aLong) {
            return false;
        }

        @Override
        public void flush() {

        }

        @Override
        public <S extends Member> S saveAndFlush(S entity) {
            return null;
        }

        @Override
        public void deleteInBatch(Iterable<Member> entities) {

        }

        @Override
        public void deleteAllInBatch() {

        }

        @Override
        public Member getOne(Long aLong) {
            return null;
        }

        @Override
        public <S extends Member> Optional<S> findOne(Example<S> example) {
            return Optional.empty();
        }

        @Override
        public <S extends Member> List<S> findAll(Example<S> example) {
            return null;
        }

        @Override
        public <S extends Member> List<S> findAll(Example<S> example, Sort sort) {
            return null;
        }

        @Override
        public <S extends Member> Page<S> findAll(Example<S> example, Pageable pageable) {
            return null;
        }

        @Override
        public <S extends Member> long count(Example<S> example) {
            return 0;
        }

        @Override
        public <S extends Member> boolean exists(Example<S> example) {
            return false;
        }

        @Override
        public List<Member> findByName(String name) {
            findByName_wasCalled = true;
            return findByName_returnValue;
        }
    }
}
