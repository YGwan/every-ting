package com.everyTing.team.application.service;

import static com.everyTing.team.common.constraints.TeamConstraints.DAILY_REQUEST_LIMIT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.mock;

import com.everyTing.team.adapter.out.persistence.entity.TeamMemberEntity;
import com.everyTing.team.adapter.out.persistence.entity.TeamRequestEntity;
import com.everyTing.team.application.port.in.command.TeamRequestCountCommand;
import com.everyTing.team.application.port.in.command.TeamRequestRemoveCommand;
import com.everyTing.team.application.port.in.command.TeamRequestSaveCommand;
import com.everyTing.team.application.port.out.TeamMemberPort;
import com.everyTing.team.application.port.out.TeamPort;
import com.everyTing.team.application.port.out.TeamRequestPort;
import com.everyTing.team.application.service.internal.TeamRequestInternalService;
import com.everyTing.team.domain.Team;
import com.everyTing.team.domain.TeamMember;
import com.everyTing.team.domain.TeamRequest;
import com.everyTing.team.utils.BaseTest;
import com.everyTing.team.utils.TeamMemberEntityFixture;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;

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
    private TeamRequestInternalService teamRequestInternalService;

    private TeamRequestEntity teamRequestEntity = TeamRequestEntityFixture.get();

    @DisplayName("남은 요청 횟수 조회")
    @Test
    void countRemainingTeamRequest() {
        Long before = 1L;
        TeamRequestCountCommand command = TeamRequestCountCommand.of(1L);

        // given
        given(teamRequestPort.countByFromTeamIdAndCreatedAtAfter(any(), any())).willReturn(before);

        // when
        Long result = sut.countRemainingTeamRequest(command);

        // then
        assertThat(result).isEqualTo(DAILY_REQUEST_LIMIT - before);
    }

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
        given(teamPort.findTeamById(any())).willReturn(Team.from(TeamRequestEntityFixture.getFromTeam()))
                                           .willReturn(Team.from(TeamRequestEntityFixture.getToTeam()));
        given(teamRequestPort.saveTeamRequest(any(), any())).willReturn(teamRequestEntity.getId());

        // when
        Long created = sut.saveTeamRequest(command);

        // then
        assertThat(created).isEqualTo(teamRequestEntity.getId());
    }

    @DisplayName("팀 요청 삭제")
    @Test
    void removeTeamRequest() {
        TeamRequestRemoveCommand command = TeamRequestRemoveCommand.of(1L, 2L);
        TeamMemberEntity fromTeamLeaderEntity = TeamMemberEntityFixture.get(1L);
        ReflectionTestUtils.setField(fromTeamLeaderEntity, "memberId", command.getMemberId());

        // given
        given(teamRequestPort.findTeamRequest(any())).willReturn(
            TeamRequest.from(teamRequestEntity));
        given(teamMemberPort.existsTeamLeaderByTeamIdAndMemberId(any(), any())).willReturn(true)
                                                                               .willReturn(false);
        given(teamMemberPort.findTeamLeader(any())).willReturn(TeamMember.from(fromTeamLeaderEntity));
        willDoNothing().given(teamRequestInternalService).removeTeamRequest(any());

        // when
        Throwable t = catchThrowable(() -> sut.removeTeamRequest(command));

        // then
        assertThat(t).doesNotThrowAnyException();
    }
}