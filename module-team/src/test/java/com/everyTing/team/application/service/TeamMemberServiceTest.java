package com.everyTing.team.application.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;

import com.everyTing.team.adapter.out.persistence.entity.TeamMemberEntity;
import com.everyTing.team.application.port.in.command.TeamMemberFindCommand;
import com.everyTing.team.application.port.in.command.TeamMemberRemoveCommand;
import com.everyTing.team.application.port.out.MemberPort;
import com.everyTing.team.application.port.out.TeamMemberPort;
import com.everyTing.team.application.port.out.TeamPort;
import com.everyTing.team.domain.TeamMember;
import com.everyTing.team.domain.TeamMembers;
import com.everyTing.team.utils.BaseTest;
import com.everyTing.team.utils.TeamMemberEntityFixture;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("팀원 service test")
@ExtendWith(MockitoExtension.class)
class TeamMemberServiceTest extends BaseTest {

    private final List<TeamMemberEntity> teamMemberEntityList = TeamMemberEntityFixture.getList();

    @InjectMocks
    private TeamMemberService sut;
    @Mock
    private TeamMemberPort teamMemberPort;
    @Mock
    private TeamPort teamPort;
    @Mock
    private MemberPort memberPort;

    @DisplayName("팀원 조회")
    @Test
    void findTeamMembers() {
        TeamMemberFindCommand command = TeamMemberFindCommand.of(1L);

        // given
        given(teamMemberPort.findTeamMembersByTeamId(command.getTeamId())).willReturn(
            TeamMembers.from(teamMemberEntityList));

        // when
        TeamMembers teamMembers = sut.findTeamMembers(command);

        // then
        assertThat(teamMembers.getTeamMembers()
                              .size()).isEqualTo(teamMemberEntityList.size());
    }

    @DisplayName("팀원 삭제")
    @Test
    void removeTeamMember() {
        TeamMember teamLeader = TeamMember.from(teamMemberEntityList.get(0));
        Long teamMemberIdToBeRemoved = teamMemberEntityList.get(1)
                                                           .getId();
        TeamMemberRemoveCommand command = TeamMemberRemoveCommand.of(teamLeader.getTeamId(),
            teamMemberIdToBeRemoved, teamLeader.getMemberId());


        // given
        given(teamMemberPort.findTeamLeader(any())).willReturn(teamLeader);
        given(teamMemberPort.existsTeamMemberByTeamIdAndTeamMemberId(any(), any()))
            .willReturn(true);
        willDoNothing().given(teamMemberPort).removeTeamMember(any(), any());

        // when
        Throwable t = catchThrowable(() -> sut.removeTeamMember(command));

        // then
        assertThat(t)
            .doesNotThrowAnyException();
    }
}
