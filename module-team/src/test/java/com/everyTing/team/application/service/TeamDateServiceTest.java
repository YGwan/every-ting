package com.everyTing.team.application.service;

import static com.everyTing.team.common.constraints.TeamConstraints.WEEKLY_DATE_LIMIT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.everyTing.team.application.port.in.command.TeamDateCountCommand;
import com.everyTing.team.application.port.out.TeamDatePort;
import com.everyTing.team.utils.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("팀 매칭 service test")
@ExtendWith(MockitoExtension.class)
class TeamDateServiceTest extends BaseTest {

    @InjectMocks
    private TeamDateService sut;
    @Mock
    private TeamDatePort teamDatePort;

    @DisplayName("남은 요청 횟수 조회")
    @Test
    void countRemainingTeamRequest() {
        Long before = 1L;
        TeamDateCountCommand command = TeamDateCountCommand.of(1L);

        // given
        given(teamDatePort.countByTeamIdAndCreatedAtAfter(any(), any())).willReturn(before);

        // when
        Long result = sut.countRemainingTeamDate(command);

        // then
        assertThat(result).isEqualTo(WEEKLY_DATE_LIMIT - before);
    }
}