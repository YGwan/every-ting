package com.everyTing.team.adapter.in.web;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.everyTing.core.token.service.TokenService;
import com.everyTing.team.adapter.in.web.request.TeamDateSaveRequest;
import com.everyTing.team.application.port.in.TeamDateUseCase;
import com.everyTing.team.utils.BaseTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(TeamDateController.class)
class TeamDateControllerTest extends BaseTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TokenService tokenService;

    @MockBean
    private TeamDateUseCase teamDateUseCase;

    @DisplayName("매칭 성사 api 테스트")
    @Test
    void teamDateSave() throws Exception {
        TeamDateSaveRequest request = new TeamDateSaveRequest(1L);

        given(teamDateUseCase.saveTeamDate(any())).willReturn(1L);

        mockMvc.perform(post("/api/v1/teams/dates").contentType(MediaType.APPLICATION_JSON)
                                                   .content(objectMapper.writeValueAsBytes(request)))
               .andExpect(status().isCreated())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$.data").value(1));
    }
}