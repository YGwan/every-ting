package com.everyTing.team.adapter.out.persistence;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.everyTing.team.adapter.out.persistence.entity.TeamMemberEntity;
import com.everyTing.team.adapter.out.persistence.entity.data.Role;
import com.everyTing.team.adapter.out.persistence.repository.TeamMemberEntityRepository;
import com.everyTing.team.domain.TeamMembers;
import com.everyTing.team.utils.TeamMemberEntityFixture;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("팀 멤버 - persistent adapter test")
@ExtendWith(MockitoExtension.class)
class TeamMemberPersistenceAdapterTest {

    private final List<TeamMemberEntity> teamMemberEntities = TeamMemberEntityFixture.get();

    @InjectMocks
    private TeamMemberPersistenceAdapter sut;
    @Mock
    private TeamMemberEntityRepository teamMemberEntityRepository;

    @DisplayName("팀 멤버 조회")
    @Test
    void findTeamMembers() {
        // given
        given(teamMemberEntityRepository.findAllByTeamIdOrderByRoleAscCreatedAtAsc(any())).willReturn(
            teamMemberEntities);

        // when
        TeamMembers teamMembers = sut.findTeamMembers(1L);

        // then
        assertThat(teamMembers.getTeamMembers()
                              .get(0)
                              .getRole()).isEqualTo(Role.LEADER);
    }
}
