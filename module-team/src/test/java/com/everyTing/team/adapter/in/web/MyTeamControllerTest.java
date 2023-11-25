package com.everyTing.team.adapter.in.web;

import static java.time.LocalDateTime.now;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.everyTing.core.token.service.TokenService;
import com.everyTing.team.adapter.out.persistence.entity.TeamDateEntity;
import com.everyTing.team.adapter.out.persistence.entity.TeamRequestEntity;
import com.everyTing.team.adapter.out.persistence.entity.data.Role;
import com.everyTing.team.application.port.in.MyTeamUseCase;
import com.everyTing.team.domain.TeamDates;
import com.everyTing.team.domain.TeamRequests;
import com.everyTing.team.utils.BaseTest;
import com.everyTing.team.utils.TeamRequestEntityFixture;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(MyTeamController.class)
class MyTeamControllerTest extends BaseTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TokenService tokenService;

    @MockBean
    private MyTeamUseCase myTeamUseCase;

    @DisplayName("내 팀 조회 api 테스트")
    @Test
    void myTeamList() throws Exception {
        given(myTeamUseCase.findMyTeams(any())).willReturn(List.of(1L, 2L));

        mockMvc.perform(get("/api/v1/my/teams").param("role", Role.LEADER.name()))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$.data.size()").value(2));
    }

    @DisplayName("내가 속한 팀 미팅 조회 api 테스트")
    @Test
    void myTeamDateList() throws Exception {
        TeamDateEntity entity = TeamDateEntity.of(1L, 2L);
        ReflectionTestUtils.setField(entity, "id", 1L);
        ReflectionTestUtils.setField(entity, "createdAt", now());
        given(myTeamUseCase.findMyTeamDates(any())).willReturn(
            TeamDates.from(List.of(entity), List.of(1L)));

        mockMvc.perform(get("/api/v1/my/teams/dates"))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$.data.size()").value(1))
               .andExpect(jsonPath("$.data.teamDates[0].size()").value(4));
    }

    @DisplayName("내가 속한 팀이 보낸 요청 조회 api 테스트")
    @Test
    void myTeamRequestList() throws Exception {
        TeamRequestEntity request = TeamRequestEntityFixture.get();

        given(myTeamUseCase.findMyTeamRequests(any())).willReturn(
            TeamRequests.from(List.of(request)));

        mockMvc.perform(get("/api/v1/my/teams/requests"))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$.data.size()").value(1))
               .andExpect(jsonPath("$.data.teamRequests[0].size()").value(4));
    }

    @DisplayName("내 팀 존재 여부 확인 api 테스트")
    @Test
    void myTeamExists() throws Exception {
        Boolean expected = true;
        given(myTeamUseCase.existsMyTeam(any())).willReturn(expected);

        mockMvc.perform(get("/api/v1/my/teams/exists").param("memberId", "1"))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$.data").value(expected));
    }

    @DisplayName("팀 나가기(팀원용) api 테스트")
    @Test
    void myTeamRemove() throws Exception {
        willDoNothing().given(myTeamUseCase).removeMyTeam(any());

        mockMvc.perform(delete("/api/v1/my/teams/1"))
               .andExpect(status().isOk());
    }
}