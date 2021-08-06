package com.pcloud.member;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@Data
public class MemberJoinDto {
    @NotEmpty
    private String name;
    private Address address;

    public MemberJoinDto(@NotEmpty String name, Address address) {
        this.name = name;
        this.address = address;
    }
}
