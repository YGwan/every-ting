package com.everyting.member;

import com.everyTing.core.domain.Gender;
import com.everyTing.core.token.data.MemberTokens;
import com.everyTing.core.token.service.TokenService;
import com.everyTing.member.controller.MemberController;
import com.everyTing.member.domain.Member;
import com.everyTing.member.dto.request.SignInRequest;
import com.everyTing.member.dto.request.SignUpRequest;
import com.everyTing.member.dto.response.MemberInfoResponse;
import com.everyTing.member.service.MemberService;
import com.everyting.member.utils.BaseTest;
import com.everyting.member.utils.MemberFixture;
import com.everyting.member.utils.TokenFixture;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MemberController.class)
public class MemberTest extends BaseTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MemberService memberService;

    @MockBean
    private TokenService tokenService;

    private final MemberTokens tokens = TokenFixture.get();

    @DisplayName("멤버 조회 테스트")
    @Test
    void findMemberById() throws Exception {
        Long memberId = 1L;
        Member member = MemberFixture.get(memberId);

        given(memberService.findMemberInfo(any())).willReturn(MemberInfoResponse.from(member));

        mockMvc.perform(get("/api/v1/members/1/info"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data.id").value(memberId));
    }

    @DisplayName("멤버 회원가입 테스트")
    @Test
    void signUpTest() throws Exception {
        SignUpRequest request = new SignUpRequest(
                "ygwan", Gender.MALE, 1998, "everyting@dankook.ac.kr", "qwer1234!",
                "everyting", "단국대", "컴퓨터공학과", "profile_url"
        );

        given(memberService.signUp(any())).willReturn(1L);
        given(tokenService.issue(any())).willReturn(tokens);

        mockMvc.perform(post("/api/v1/members/signUp").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(request)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data.accessToken").value("accessToken"))
                .andExpect(jsonPath("$.data.refreshToken").value("refreshToken"));
    }

    @DisplayName("멤버 로그인 테스트")
    @Test
    void signInTest() throws Exception {
        SignInRequest request = new SignInRequest("everyting@dankook.ac.kr", "qwer1234!");

        given(memberService.signUp(any())).willReturn(1L);
        given(tokenService.issue(any())).willReturn(tokens);

        mockMvc.perform(post("/api/v1/members/signIn").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data.accessToken").value("accessToken"))
                .andExpect(jsonPath("$.data.refreshToken").value("refreshToken"));
    }
}
