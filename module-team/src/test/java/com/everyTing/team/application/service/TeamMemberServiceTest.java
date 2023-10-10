package com.everyTing.team.application.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import com.everyTing.team.adapter.out.persistence.entity.TeamMemberEntity;
import com.everyTing.team.application.port.in.command.TeamMemberFindCommand;
import com.everyTing.team.application.port.out.TeamMemberPort;
import com.everyTing.team.domain.TeamMembers;
import com.everyTing.team.utils.TeamMemberEntityFixture;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("팀 멤버 service test")
@ExtendWith(MockitoExtension.class)
class TeamMemberServiceTest {

    private final List<TeamMemberEntity> teamMemberEntityList = TeamMemberEntityFixture.get();

    @InjectMocks
    private TeamMemberService sut;
    @Mock
    private TeamMemberPort teamMemberPort;

    @DisplayName("팀 멤버 조회")
    @Test
    void findTeamMembers() {
        TeamMemberFindCommand command = TeamMemberFindCommand.of(1L);

        // given
        given(teamMemberPort.findTeamMembers(command.getTeamId())).willReturn(
            TeamMembers.from(teamMemberEntityList));

        // when
        TeamMembers teamMembers = sut.findMembers(command);

        // then
        assertThat(teamMembers.getTeamMembers()
                              .size()).isEqualTo(teamMemberEntityList.size());
    }


}