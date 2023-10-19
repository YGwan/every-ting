package com.everyTing.team.application.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.everyTing.team.application.port.in.command.TeamRequestSaveCommand;
import com.everyTing.team.application.port.out.TeamMemberPort;
import com.everyTing.team.application.port.out.TeamRequestPort;
import com.everyTing.team.utils.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("팀 요청 service test")
@ExtendWith(MockitoExtension.class)
class TeamRequestServiceTest extends BaseTest {

    @InjectMocks
    private TeamRequestService sut;
    @Mock
    private TeamMemberPort teamMemberPort;
    @Mock
    private TeamRequestPort teamRequestPort;

    @DisplayName("팀 요청 저장")
    @Test
    void saveTeamRequest() {
        TeamRequestSaveCommand command = TeamRequestSaveCommand.of(1L, 2L, 1L);
        Long expected = 1L;

        // given
        given(teamMemberPort.existsTeamLeaderByTeamIdAndMemberId(any(), any())).willReturn(true);
        given(teamRequestPort.countTodayRequest(any())).willReturn(1L);
        given(teamRequestPort.saveTeamRequest(any(), any())).willReturn(expected);

        // when
        Long created = sut.saveTeamRequest(command);

        // then
        assertThat(created).isEqualTo(expected);
    }
}