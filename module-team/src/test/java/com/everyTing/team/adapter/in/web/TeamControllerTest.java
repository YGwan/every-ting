package com.everyTing.team.adapter.in.web;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.everyTing.team.adapter.in.web.request.TeamSaveRequest;
import com.everyTing.team.application.port.in.TeamUseCase;
import com.everyTing.team.utils.BaseTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(TeamController.class)
class TeamControllerTest extends BaseTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TeamUseCase teamUseCase;

    @DisplayName("팀 생성 api 테스트")
    @Test
    void teamSave() throws Exception {
        TeamSaveRequest request = new TeamSaveRequest("여기여기 모여라", (short) 3, "경기 남부",
            List.of("모두E", "수원"));

        given(teamUseCase.saveTeam(any())).willReturn(1L);

        mockMvc.perform(post("/api/v1/teams").contentType(MediaType.APPLICATION_JSON)
                                             .content(objectMapper.writeValueAsBytes(request)))
               .andExpect(status().isCreated())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$.data").value(1));
    }
}