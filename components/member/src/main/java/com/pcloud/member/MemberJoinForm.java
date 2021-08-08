package com.pcloud.member;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class MemberJoinForm {
    final String name;
    final Address address;

    public MemberJoinForm(String name, Address address) {
        this.name = name;
        this.address = address;
    }
}
