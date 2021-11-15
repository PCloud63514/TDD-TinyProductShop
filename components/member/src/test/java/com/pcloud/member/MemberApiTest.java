package com.pcloud.member;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MemberApiTest {
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MemberApi memberApi = new MemberApi(null);
        mockMvc = MockMvcBuilders.standaloneSetup(memberApi).build();
    }

    @Test
    void hello_isOk() throws Exception {
        mockMvc.perform(get("/member/"))
                .andExpect(status().isOk());
    }

    @Test
    void hello_returnsHello() throws Exception {
        mockMvc.perform(get("/member/"))
                .andExpect(content().string("hello"));
    }
}
