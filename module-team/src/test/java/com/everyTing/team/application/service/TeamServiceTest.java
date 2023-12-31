package com.everyTing.team.application.service;

import static com.everyTing.team.utils.TeamEntityFixture.code;
import static com.everyTing.team.utils.TeamEntityFixture.memberLimit;
import static com.everyTing.team.utils.TeamEntityFixture.name;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.verify;

import com.everyTing.core.feign.dto.Member;
import com.everyTing.team.adapter.out.persistence.entity.TeamEntity;
import com.everyTing.team.adapter.out.persistence.entity.TeamMemberEntity;
import com.everyTing.team.adapter.out.persistence.entity.data.Code;
import com.everyTing.team.adapter.out.persistence.entity.data.Name;
import com.everyTing.team.application.port.in.command.TeamFindByCodeCommand;
import com.everyTing.team.application.port.in.command.TeamFindByIdCommand;
import com.everyTing.team.application.port.in.command.TeamRemoveCommand;
import com.everyTing.team.application.port.in.command.TeamSaveCommand;
import com.everyTing.team.application.port.out.MemberPort;
import com.everyTing.team.application.port.out.TeamMemberPort;
import com.everyTing.team.application.port.out.TeamPort;
import com.everyTing.team.common.util.TeamCodeGenerator;
import com.everyTing.team.domain.Team;
import com.everyTing.team.domain.TeamMember;
import com.everyTing.team.utils.BaseTest;
import com.everyTing.team.utils.MemberFixture;
import com.everyTing.team.utils.TeamEntityFixture;
import com.everyTing.team.utils.TeamMemberEntityFixture;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("팀 service test")
@ExtendWith(MockitoExtension.class)
class TeamServiceTest extends BaseTest {

    private final Member member = MemberFixture.get(1L);
    private final TeamEntity teamEntity = TeamEntityFixture.get(1L);

    @InjectMocks
    private TeamService sut;
    @Mock
    private TeamPort teamPort;
    @Mock
    private MemberPort memberPort;
    @Mock
    private TeamMemberPort teamMemberPort;
    @Mock
    private TeamCodeGenerator teamCodeGenerator;

    @DisplayName("id로 팀 조회")
    @Test
    void findTeamById() {
        TeamFindByIdCommand command = TeamFindByIdCommand.of(1L);

        // given
        given(teamPort.findTeamById(command.getTeamId())).willReturn(Team.from(teamEntity));

        // when
        Team created = sut.findTeamById(command);

        // then
        assertThat(created.getId()).isEqualTo(command.getTeamId());
    }

    @DisplayName("code로 팀 조회")
    @Test
    void findTeamByCode() {
        TeamFindByCodeCommand command = TeamFindByCodeCommand.of(code.getValue());

        // given
        given(teamPort.findTeamByCode(command.getCode())).willReturn(Team.from(teamEntity));

        // when
        Team created = sut.findTeamByCode(command);

        // then
        assertThat(created.getCode()).isEqualTo(command.getCode()
                                                       .getValue());
    }

    @DisplayName("팀 생성")
    @Test
    void saveTeam() {
        TeamSaveCommand command = TeamSaveCommand.of(member.getId(), name.getValue(),
            memberLimit.getValue(), List.of("서울", "인천"), List.of("모두E", "보드게임"));
        Long createdTeamMemberId = 1L;

        // given
        given(memberPort.getMemberById(any())).willReturn(member);
        given(teamPort.existsTeamByCode(any())).willReturn(false);
        given(teamPort.saveTeam(any(), any(), any(), any(), any(), any(), any(), any(),
            any())).willReturn(teamEntity.getId());
        given(teamMemberPort.existsTeamLeaderByMemberId(any())).willReturn(false);
        given(teamMemberPort.saveTeamLeader(any(), any())).willReturn(createdTeamMemberId);

        // when
        Long createdTeamId = sut.saveTeam(command);

        // then
        assertThat(createdTeamId).isEqualTo(teamEntity.getId());
    }

    @DisplayName("팀 삭제")
    @Test
    void removeTeam() {
        TeamRemoveCommand command = TeamRemoveCommand.of(member.getId(), teamEntity.getId());
        teamEntity.decreaseMemberNumber();
        teamEntity.decreaseMemberNumber();
        TeamMemberEntity teamLeaderEntity = TeamMemberEntityFixture.getList().get(0);

        // given
        given(teamMemberPort.findTeamLeader(any())).willReturn(TeamMember.from(teamLeaderEntity));
        given(teamPort.findTeamById(any())).willReturn(Team.from(teamEntity));
        willDoNothing().given(teamPort).removeTeam(any());

        // when
        Throwable t = catchThrowable(() -> sut.removeTeam(command));

        // then
        assertThat(t).doesNotThrowAnyException();
        then(teamPort).should().removeTeam(command.getTeamId());
    }
}
