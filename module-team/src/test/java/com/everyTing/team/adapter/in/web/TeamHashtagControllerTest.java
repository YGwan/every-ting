package com.everyTing.team.adapter.in.web;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.everyTing.core.token.service.TokenService;
import com.everyTing.team.adapter.in.web.request.TeamHashtagModifyRequest;
import com.everyTing.team.adapter.out.persistence.entity.TeamHashtagEntity;
import com.everyTing.team.application.port.in.TeamHashtagUseCase;
import com.everyTing.team.domain.TeamHashtags;
import com.everyTing.team.utils.BaseTest;
import com.everyTing.team.utils.TeamHashtagEntityFixture;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(TeamHashtagController.class)
class TeamHashtagControllerTest extends BaseTest {

    private final List<TeamHashtagEntity> teamHashtagEntities = TeamHashtagEntityFixture.get();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TokenService tokenService;

    @MockBean
    private TeamHashtagUseCase teamHashtagUseCase;

    @DisplayName("팀 해시태그 조회 api 테스트")
    @Test
    void hashtagList() throws Exception {
        given(teamHashtagUseCase.findHashtags(any())).willReturn(
            TeamHashtags.from(teamHashtagEntities));

        mockMvc.perform(get("/api/v1/teams/1/hashtags"))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$.data.size()").value(1))
               .andExpect(jsonPath("$.data.hashtags.size()").value(teamHashtagEntities.size()));
    }

    @DisplayName("팀 해시태그 수정 api 테스트")
    @Test
    void hashtagModify() throws Exception {
        TeamHashtagModifyRequest request =
            new TeamHashtagModifyRequest(List.of(1L, 2L), List.of("새로운해시"));

        willDoNothing().given(teamHashtagUseCase)
                       .modifyHashtags(any());

        mockMvc.perform(put("/api/v1/teams/1/hashtags")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsBytes(request)))
            .andExpect(status().isOk());
    }
}