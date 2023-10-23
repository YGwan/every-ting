package com.everyTing.team.adapter.in.web;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.everyTing.core.token.service.TokenService;
import com.everyTing.team.adapter.out.persistence.entity.TeamEntity;
import com.everyTing.team.adapter.out.persistence.entity.TeamLikeEntity;
import com.everyTing.team.adapter.out.persistence.entity.TeamMemberEntity;
import com.everyTing.team.application.port.in.TeamLikeUseCase;
import com.everyTing.team.domain.TeamLikes;
import com.everyTing.team.utils.BaseTest;
import com.everyTing.team.utils.TeamEntityFixture;
import com.everyTing.team.utils.TeamMemberEntityFixture;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(TeamLikeController.class)
class TeamLikeControllerTest extends BaseTest {

    TeamMemberEntity member1 = TeamMemberEntityFixture.get(1L);
    TeamMemberEntity member2 = TeamMemberEntityFixture.get(2L);
    TeamEntity fromTeam = TeamEntityFixture.get(1L);
    TeamEntity toTeam = TeamEntityFixture.get(2L);
    TeamLikeEntity teamLike1 = TeamLikeEntity.of(member1, fromTeam, toTeam);
    TeamLikeEntity teamLike2 = TeamLikeEntity.of(member2, fromTeam, toTeam);
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TokenService tokenService;
    @MockBean
    private TeamLikeUseCase teamLikeUseCase;
    @Autowired
    private ObjectMapper objectMapper;

    @DisplayName("팀 좋아요 조회 api 테스트")
    @Test
    void teamLikeList() throws Exception {
        given(teamLikeUseCase.findTeamLike(any())).willReturn(
            TeamLikes.from(List.of(teamLike1, teamLike2)));

        mockMvc.perform(get("/api/v1/teams/likes")
                   .param("fromTeamId", "1")
                   .param("toTeamId", "2"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.data.fromTeamMemberIds.size()").value(2))
               .andExpect(jsonPath("$.data").hasJsonPath());
    }

    @DisplayName("팀 좋아요 api 테스트")
    @Test
    void teamLikeSave() throws Exception {
        willDoNothing().given(teamLikeUseCase)
                       .saveTeamLike(any());

        mockMvc.perform(post("/api/v1/teams/likes")
                   .param("fromTeamId", "1")
                   .param("toTeamId", "2"))
               .andExpect(status().isCreated())
               .andExpect(jsonPath("$").doesNotHaveJsonPath());
    }

    @DisplayName("팀 좋아요 취소 api 테스트")
    @Test
    void teamLikeRemove() throws Exception {
        willDoNothing().given(teamLikeUseCase)
                       .removeTeamLike(any());

        mockMvc.perform(delete("/api/v1/teams/likes")
                   .param("fromTeamId", "1")
                   .param("toTeamId", "2"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$").doesNotHaveJsonPath());
    }
}