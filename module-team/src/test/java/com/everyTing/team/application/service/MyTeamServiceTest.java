package com.everyTing.team.application.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.everyTing.team.adapter.out.persistence.entity.TeamDateEntity;
import com.everyTing.team.adapter.out.persistence.entity.TeamMemberEntity;
import com.everyTing.team.adapter.out.persistence.entity.data.Role;
import com.everyTing.team.application.port.in.command.MyTeamDateFindCommand;
import com.everyTing.team.application.port.in.command.MyTeamFindCommand;
import com.everyTing.team.application.port.out.TeamDatePort;
import com.everyTing.team.application.port.out.TeamMemberPort;
import com.everyTing.team.domain.TeamDates;
import com.everyTing.team.domain.TeamMembers;
import com.everyTing.team.utils.BaseTest;
import com.everyTing.team.utils.TeamMemberEntityFixture;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfSystemProperties;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

@DisplayName("내 팀 service test")
@ExtendWith(MockitoExtension.class)
class MyTeamServiceTest extends BaseTest {

    @InjectMocks
    private MyTeamService sut;
    @Mock
    private TeamMemberPort teamMemberPort;
    @Mock
    private TeamDatePort teamDatePort;

    @DisplayName("내 팀 조회")
    @Test
    void findMyTeam() {
        MyTeamFindCommand command = MyTeamFindCommand.of(1L, Role.MEMBER);

        TeamMemberEntity mockEntity1 = TeamMemberEntity.of(1L, 1L, Role.MEMBER);
        TeamMemberEntity mockEntity2 = TeamMemberEntity.of(2L, 1L, Role.MEMBER);

        TeamMembers teamMembers = TeamMembers.from(List.of(mockEntity1, mockEntity2));

        // given
        given(teamMemberPort.findTeamMembersByMemberIdAndRole(any(), any())).willReturn(
            teamMembers);

        // when
        List<Long> teamIds = sut.findMyTeams(command);

        // then
        assertThat(teamIds).contains(1L);
        assertThat(teamIds).contains(2L);
    }

    @DisplayName("내 팀 미팅 조회")
    @Test
    void findMyTeamDates() {
        MyTeamDateFindCommand command = MyTeamDateFindCommand.of(1L);
        TeamMemberEntity teamMemberRecord1 = TeamMemberEntityFixture.get(1L);
        TeamMemberEntity teamMemberRecord2 = TeamMemberEntityFixture.get(2L);
        ReflectionTestUtils.setField(teamMemberRecord2, "teamId", teamMemberRecord1.getTeamId() + 1);
        TeamDateEntity expected = TeamDateEntity.of(teamMemberRecord1.getTeamId(), 100L);

        // given
        given(teamMemberPort.findTeamMembersByMemberId(any())).willReturn(
            TeamMembers.from(List.of(teamMemberRecord1, teamMemberRecord2)));
        given(teamDatePort.findTeamDatesByTeamIdIn(any())).willReturn(
            TeamDates.from(List.of(expected),
                List.of(teamMemberRecord1.getTeamId(), teamMemberRecord2.getTeamId())));

        // when
        TeamDates result = sut.findMyTeamDates(command);

        // then
        assertThat(result.getTeamDates()
                         .size()).isEqualTo(1);
        assertThat(result.getTeamDates()
                         .get(0)
                         .getMyTeamId()).isEqualTo(teamMemberRecord1.getTeamId());
    }
}