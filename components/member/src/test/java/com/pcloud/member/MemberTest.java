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
        Member member = Member.builder()
                .name("name")
                .build();

        assertThat(member.getName()).isEqualTo("name");
        assertThat(member.getId()).isEqualTo(null);
    }
}
