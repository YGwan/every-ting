package com.everyTing.team.adapter.in.web;

import static java.time.LocalDateTime.now;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.everyTing.core.token.service.TokenService;
import com.everyTing.team.adapter.out.persistence.entity.TeamDateEntity;
import com.everyTing.team.adapter.out.persistence.entity.data.Role;
import com.everyTing.team.application.port.in.MyTeamUseCase;
import com.everyTing.team.domain.TeamDates;
import com.everyTing.team.utils.BaseTest;
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
        given(myTeamUseCase.findMyTeamDates(any())).willReturn(TeamDates.from(List.of(entity), List.of(1L)));

        mockMvc.perform(get("/api/v1/my/teams/dates"))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$.data.size()").value(1))
               .andExpect(jsonPath("$.data.teamDates[0].size()").value(4));
    }
}