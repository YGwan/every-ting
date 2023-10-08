package com.everyTing.team.application.service;

import static com.everyTing.team.utils.TeamEntityFixture.memberLimit;
import static com.everyTing.team.utils.TeamEntityFixture.name;
import static com.everyTing.team.utils.TeamEntityFixture.region;
import static com.everyTing.team.utils.TeamHashtagFixture.hashtags;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import com.everyTing.core.feign.dto.Member;
import com.everyTing.team.adapter.out.persistence.entity.TeamEntity;
import com.everyTing.team.application.port.in.command.TeamFindCommand;
import com.everyTing.team.application.port.in.command.TeamSaveCommand;
import com.everyTing.team.application.port.out.MemberPort;
import com.everyTing.team.application.port.out.TeamMemberPort;
import com.everyTing.team.application.port.out.TeamPort;
import com.everyTing.team.domain.Team;
import com.everyTing.team.utils.BaseTest;
import com.everyTing.team.utils.MemberFixture;
import com.everyTing.team.utils.TeamEntityFixture;
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

    @DisplayName("팀 조회")
    @Test
    void findTeam() {
        TeamFindCommand command = TeamFindCommand.of(1L);

        // given
        given(teamPort.findTeam(command.getTeamId())).willReturn(Team.from(teamEntity));

        // when
        Team created = sut.findTeam(command);

        // then
        assertThat(created.getId()).isEqualTo(command.getTeamId());
    }

    @DisplayName("팀 생성")
    @Test
    void saveTeam() {
        TeamSaveCommand command = TeamSaveCommand.of(member.getMemberId(), name.getValue(),
            memberLimit.getValue(), region.getValue(), hashtags);
        Long createdTeamMemberId = 1L;

        // given
        given(memberPort.getMemberById(any())).willReturn(member);
        given(teamPort.saveTeam(any(), any(), any(), any(), any(), any(), any(), any(),
            any())).willReturn(teamEntity.getId());
        given(teamMemberPort.existsTeamMemberByTeamLeaderId(any())).willReturn(false);
        given(teamMemberPort.saveTeamMember(any(), any(), any())).willReturn(createdTeamMemberId);

        // when
        Long createdTeamId = sut.saveTeam(command);

        // then
        assertThat(createdTeamId).isEqualTo(teamEntity.getId());
    }

}