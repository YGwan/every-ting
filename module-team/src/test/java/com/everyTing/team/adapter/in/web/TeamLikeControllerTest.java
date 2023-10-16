package com.everyTing.team.adapter.in.web;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.everyTing.core.token.service.TokenService;
import com.everyTing.team.adapter.in.web.request.TeamLikeRequest;
import com.everyTing.team.adapter.in.web.request.TeamUnlikeRequest;
import com.everyTing.team.application.port.in.TeamLikeUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(TeamLikeController.class)
class TeamLikeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TokenService tokenService;

    @MockBean
    private TeamLikeUseCase teamLikeUseCase;

    @Autowired
    private ObjectMapper objectMapper;

    @DisplayName("팀 좋아요 api 테스트")
    @Test
    void teamLikeSave() throws Exception {
        TeamLikeRequest request = new TeamLikeRequest(1L);

        willDoNothing().given(teamLikeUseCase)
                       .saveTeamLike(any());

        mockMvc.perform(post("/api/v1/teams/1/likes").param("toTeamId", "1"))
               .andExpect(status().isCreated())
               .andExpect(jsonPath("$").doesNotHaveJsonPath());
    }

    @DisplayName("팀 좋아요 취소 api 테스트")
    @Test
    void teamLikeRemove() throws Exception {
        TeamUnlikeRequest request = new TeamUnlikeRequest(1L);

        willDoNothing().given(teamLikeUseCase)
                       .removeTeamLike(any());

        mockMvc.perform(delete("/api/v1/teams/1/likes").param("toTeamId", "1"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$").doesNotHaveJsonPath());
    }
}