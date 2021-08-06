import com.fasterxml.jackson.databind.ObjectMapper;
import com.pcloud.CoreApplication;
import com.pcloud.member.Address;
import com.pcloud.member.Member;
import com.pcloud.member.MemberApi;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.junit.jupiter.api.Assertions.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MemberApi.class)
@SpringBootTest
class MemberApiTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void hello() throws Exception {
        mockMvc.perform(get("/member/")).andExpect(status().isCreated());
    }

    @Test
    void join_callSaveMember_MemberService() throws Exception {
        Address givenAddress = Address.builder()
                .city("city")
                .street("street")
                .zipcode("zipcode")
                .build();
        Member givenMember = Member.builder()
                .name("name")
                .address(givenAddress)
                .build();

        String om = objectMapper.writeValueAsString(givenMember);

        mockMvc.perform(post("")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om)).andExpect(status().isCreated());
    }
}
