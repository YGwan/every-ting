package com.everyTing.team.adapter.in.web;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.everyTing.core.token.service.TokenService;
import com.everyTing.team.adapter.out.persistence.entity.TeamMemberEntity;
import com.everyTing.team.application.port.in.TeamMemberUseCase;
import com.everyTing.team.domain.TeamMembers;
import com.everyTing.team.utils.BaseTest;
import com.everyTing.team.utils.TeamMemberEntityFixture;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(TeamMemberController.class)
class TeamMemberControllerTest extends BaseTest {

    private final List<TeamMemberEntity> teamMemberEntities = TeamMemberEntityFixture.get();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TokenService tokenService;

    @MockBean
    private TeamMemberUseCase teamMemberUseCase;

    @DisplayName("팀 멤버 조회 api 테스트")
    @Test
    void memberList() throws Exception {
        given(teamMemberUseCase.findMembers(any())).willReturn(TeamMembers.from(teamMemberEntities));

        mockMvc.perform(get("/api/v1/teams/1/members"))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$.data.teamMembers.size()").value(teamMemberEntities.size()));
    }
}