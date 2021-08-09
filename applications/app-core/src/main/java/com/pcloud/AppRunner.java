package com.pcloud;

import com.pcloud.member.Address;
import com.pcloud.member.Member;
import com.pcloud.member.MemberJoinForm;
import com.pcloud.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements ApplicationRunner {
    @Autowired
    MemberService memberService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        initMember();
    }

    private void initMember() {
        for (int i = 0; i < 10; i++) {
            Address address = Address.builder()
                    .city("city_" + ((i % 10) + 1))
                    .street("street_" + ((i % 5) + 1))
                    .zipcode("zipcode_" + ((i % 4) + 1))
                    .build();

            MemberJoinForm memberJoinForm = new MemberJoinForm("name_" + i + 1, address);

            memberService.saveMember(memberJoinForm);
        }
    }
}
