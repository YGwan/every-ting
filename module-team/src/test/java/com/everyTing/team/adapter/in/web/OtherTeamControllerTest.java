package com.everyTing.team.adapter.in.web;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.everyTing.core.token.service.TokenService;
import com.everyTing.team.adapter.out.persistence.projection.TeamEntityWithLikeCount;
import com.everyTing.team.application.port.in.OtherTeamUseCase;
import com.everyTing.team.domain.Team;
import com.everyTing.team.domain.TeamWithLikeCount;
import com.everyTing.team.utils.BaseTest;
import com.everyTing.team.utils.TeamEntityFixture;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(OtherTeamController.class)
class OtherTeamControllerTest extends BaseTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TokenService tokenService;

    @MockBean
    private OtherTeamUseCase otherTeamUseCase;

    @DisplayName("상대팀 리스트 조회 api 테스트")
    @Test
    void teamList() throws Exception {
        Slice<TeamWithLikeCount> teamSlice = new SliceImpl<>(List.of(TeamWithLikeCount.from(
                new TeamEntityWithLikeCount(TeamEntityFixture.get(1L), 2L))),
            PageRequest.of(0, 20), false);
        given(otherTeamUseCase.findTeamList(any())).willReturn(teamSlice);

        mockMvc.perform(get("/api/v1/teams/1/other-gender-teams"))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$.meta.size()").value(4))
               .andExpect(jsonPath("$.meta.numOfElements").value(1))
               .andExpect(jsonPath("$.meta.last").value(true))
               .andExpect(jsonPath("$.data.size()").value(1));
    }

}