package com.pcloud.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberTest {
    @Test
    void member_has_noArgs() {
        Member member = new Member();
    }

    @Test
    void member_has_fields() {
        Address givenAddress = new Address();

        Member member = Member.builder()
                .name("name")
                .address(givenAddress)
                .build();

        assertThat(member.getName()).isEqualTo("name");
        assertThat(member.getAddress()).isEqualTo(givenAddress);
        assertThat(member.getId()).isEqualTo(null);
    }
}
