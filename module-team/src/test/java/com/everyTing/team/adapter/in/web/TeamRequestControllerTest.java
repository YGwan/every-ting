package com.everyTing.team.adapter.in.web;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.everyTing.core.token.service.TokenService;
import com.everyTing.team.adapter.in.web.request.TeamRequestSaveRequest;
import com.everyTing.team.adapter.out.persistence.entity.TeamRequestEntity;
import com.everyTing.team.application.port.in.TeamRequestUseCase;
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
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(TeamRequestController.class)
class TeamRequestControllerTest extends BaseTest {

    private final TeamRequestEntity teamRequestEntity = TeamRequestEntityFixture.get();

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
        TeamRequestSaveRequest request = new TeamRequestSaveRequest(1L, 2L);
        given(teamRequestUseCase.saveTeamRequest(any())).willReturn(1L);

        mockMvc.perform(post("/api/v1/teams/requests").contentType(MediaType.APPLICATION_JSON)
                                                      .content(objectMapper.writeValueAsBytes(request)))
               .andExpect(status().isCreated())
               .andExpect(jsonPath("$.data").value(1));
    }

    @DisplayName("팀 과팅 요청 조회 api 테스트")
    @Test
    void requestDetails() throws Exception {
        given(teamRequestUseCase.findTeamRequest(any())).willReturn(
            TeamRequests.from(List.of(teamRequestEntity)));

        mockMvc.perform(get("/api/v1/teams/requests")
                   .param("fromTeamId", "1")
                   .param("toTeamId", "2"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.data.teamRequests.size()").value(1));
    }
}
