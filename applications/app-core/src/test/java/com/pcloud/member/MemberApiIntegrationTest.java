package com.pcloud.member;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class MemberApiIntegrationTest {
    private MockMvc mockMvc;

    @Autowired
    MemberApi memberApi;
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberService memberService;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(memberApi).build();
    }

    @Test
    void hello() throws Exception {
        mockMvc.perform(get("/member"))
                .andExpect(content().string("hello"))
                .andDo(print());
    }

    @Test
    void getMembers() throws Exception {
        List<Member> expectedMembers = memberRepository.findAll();
        mockMvc.perform(get("/member/list"))
                .andExpect(content().json(objectMapper.writeValueAsString(expectedMembers)))
                .andDo(print());
    }

    @Test
    void join() throws Exception {
        MemberJoinForm givenMemberJoinForm = new MemberJoinForm("name", Address.builder()
                .city("city")
                .street("street")
                .zipcode("zipcode")
                .build());

        MvcResult result = mockMvc.perform(post("/member/join")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(givenMemberJoinForm)))
                .andDo(print())
                .andReturn();

        Long actualId = Long.valueOf(result.getResponse().getContentAsString());
        Member actualMember = memberService.findMemberById(actualId);

        assertThat(actualMember.getName()).isEqualTo(givenMemberJoinForm.getName());
    }

    //    @Test
//    void join_callSaveMember_MemberService() throws Exception {
//        Address givenAddress = Address.builder()
//                .city("city")
//                .street("street")
//                .zipcode("zipcode")
//                .build();
//        Member givenMember = Member.builder()
//                .name("name")
//                .address(givenAddress)
//                .build();
//
//        String om = objectMapper.writeValueAsString(givenMember);
//
//        mockMvc.perform(post("")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(om)).andExpect(status().isCreated());
//    }
}
