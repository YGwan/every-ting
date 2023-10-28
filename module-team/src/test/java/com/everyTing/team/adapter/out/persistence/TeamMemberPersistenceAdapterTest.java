package com.everyTing.team.adapter.out.persistence;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;

import com.everyTing.team.adapter.out.persistence.entity.TeamEntity;
import com.everyTing.team.adapter.out.persistence.entity.TeamMemberEntity;
import com.everyTing.team.adapter.out.persistence.entity.data.Role;
import com.everyTing.team.adapter.out.persistence.repository.TeamEntityRepository;
import com.everyTing.team.adapter.out.persistence.repository.TeamMemberEntityRepository;
import com.everyTing.team.domain.TeamMembers;
import com.everyTing.team.utils.BaseTest;
import com.everyTing.team.utils.MemberFixture;
import com.everyTing.team.utils.TeamEntityFixture;
import com.everyTing.team.utils.TeamMemberEntityFixture;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("팀원 - persistent adapter test")
@ExtendWith(MockitoExtension.class)
class TeamMemberPersistenceAdapterTest extends BaseTest {

    private final List<TeamMemberEntity> teamMemberEntities = TeamMemberEntityFixture.getList();

    @InjectMocks
    private TeamMemberPersistenceAdapter sut;
    @Mock
    private TeamMemberEntityRepository teamMemberEntityRepository;
    @Mock
    private TeamEntityRepository teamEntityRepository;

    @DisplayName("팀원 조회")
    @Test
    void findTeamMembers() {
        // given
        given(
            teamMemberEntityRepository.findAllByTeamIdOrderByRoleAscCreatedAtAsc(any())).willReturn(
            teamMemberEntities);

        // when
        TeamMembers teamMembers = sut.findTeamMembersByTeamId(1L);

        // then
        assertThat(teamMembers.getTeamMembers()
                              .get(0)
                              .getRole()).isEqualTo(Role.LEADER);
    }

    @DisplayName("팀원 추가")
    @Test
    void saveTeamMember() {
        Long teamId = 1L;
        Long expected = 1L;
        TeamEntity teamEntity = TeamEntityFixture.get(teamId);
        Short memberNumberBefore = teamEntity.getMemberNumber();

        // given
        given(teamEntityRepository.findByIdWithPessimisticLock(teamId)).willReturn(
            Optional.of(teamEntity));
        given(teamMemberEntityRepository.save(any())).willReturn(
            TeamMemberEntityFixture.get(expected));

        // when
        Long createdId = sut.saveTeamMember(teamId, MemberFixture.get(1L));

        // then
        assertThat(createdId).isEqualTo(expected);
        assertThat(teamEntity.getMemberNumber()).isEqualTo((short)(memberNumberBefore + 1));
    }

    @DisplayName("팀원 추방")
    @Test
    void removeTeamMember() {
        Long teamId = 1L;
        TeamEntity teamEntity = TeamEntityFixture.get(teamId);
        Short before = teamEntity.getMemberNumber();

        // given
        willDoNothing().given(teamMemberEntityRepository)
                       .deleteById(any());
        given(teamEntityRepository.findByIdWithPessimisticLock(any())).willReturn(Optional.of(teamEntity));

        // when
        Throwable t = catchThrowable(() -> sut.removeTeamMember(1L, 1L));

        // then
        assertThat(t).doesNotThrowAnyException();
        assertThat(teamEntity.getMemberNumber()).isEqualTo((short)(before - 1));
    }
}
