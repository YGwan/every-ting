package com.everyTing.team.application.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.everyTing.team.adapter.out.persistence.entity.TeamMemberEntity;
import com.everyTing.team.adapter.out.persistence.entity.data.Role;
import com.everyTing.team.application.port.in.command.MyTeamFindCommand;
import com.everyTing.team.application.port.out.TeamMemberPort;
import com.everyTing.team.domain.TeamMembers;
import com.everyTing.team.utils.BaseTest;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfSystemProperties;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("내 팀 service test")
@ExtendWith(MockitoExtension.class)
class MyTeamServiceTest extends BaseTest {

    @InjectMocks
    private MyTeamService sut;
    @Mock
    private TeamMemberPort teamMemberPort;

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
}