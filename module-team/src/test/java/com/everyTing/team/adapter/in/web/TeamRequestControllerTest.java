package com.everyTing.team.adapter.in.web;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.everyTing.core.token.service.TokenService;
import com.everyTing.team.application.port.in.TeamRequestUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(TeamRequestController.class)
class TeamRequestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TokenService tokenService;

    @MockBean
    private TeamRequestUseCase teamRequestUseCase;

    @DisplayName("팀 과팅 요청 api 테스트")
    @Test
    void requestSave() throws Exception {
        given(teamRequestUseCase.saveTeamRequest(any())).willReturn(1L);

        mockMvc.perform(post("/api/v1/teams/1/requests").param("toTeamId", "1"))
               .andExpect(status().isCreated())
               .andExpect(jsonPath("$.data").value(1));
    }

}