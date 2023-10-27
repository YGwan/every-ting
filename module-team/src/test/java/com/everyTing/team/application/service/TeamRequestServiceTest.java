package com.everyTing.team.application.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import com.everyTing.team.adapter.out.persistence.entity.TeamRequestEntity;
import com.everyTing.team.application.port.in.command.TeamRequestSaveCommand;
import com.everyTing.team.application.port.out.TeamMemberPort;
import com.everyTing.team.application.port.out.TeamPort;
import com.everyTing.team.application.port.out.TeamRequestPort;
import com.everyTing.team.domain.Team;
import com.everyTing.team.utils.BaseTest;
import com.everyTing.team.utils.TeamRequestEntityFixture;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

@DisplayName("팀 요청 service test")
@ExtendWith(MockitoExtension.class)
class TeamRequestServiceTest extends BaseTest {

    @InjectMocks
    private TeamRequestService sut;
    @Mock
    private TeamMemberPort teamMemberPort;
    @Mock
    private TeamRequestPort teamRequestPort;
    @Mock
    private TeamPort teamPort;
    @Mock
    private RedissonClient redissonClient;
    @Mock
    RLock lock;

    private TeamRequestEntity teamRequestEntity = TeamRequestEntityFixture.get();

    @DisplayName("팀 요청 저장")
    @Test
    void saveTeamRequest() throws InterruptedException {
        TeamRequestSaveCommand command = TeamRequestSaveCommand.of(1L, 2L, 1L);
        RLock mockLock = mock(RLock.class);

        // given
        given(teamMemberPort.existsTeamLeaderByTeamIdAndMemberId(any(), any())).willReturn(true);
        given(teamRequestPort.existsTeamRequest(any(), any())).willReturn(false);
        given(redissonClient.getLock(any())).willReturn(mockLock);
        given(mockLock.tryLock(3, 10, TimeUnit.SECONDS)).willReturn(true);
        given(teamRequestPort.countByFromTeamIdAndCreatedAtAfter(any(), any())).willReturn(1L);
        given(teamPort.findTeamById(any())).willReturn(Team.from(teamRequestEntity.getFromTeam()))
                                           .willReturn(Team.from(teamRequestEntity.getToTeam()));
        given(teamRequestPort.saveTeamRequest(any(), any())).willReturn(teamRequestEntity.getId());

        // when
        Long created = sut.saveTeamRequest(command);

        // then
        assertThat(created).isEqualTo(teamRequestEntity.getId());
    }
}