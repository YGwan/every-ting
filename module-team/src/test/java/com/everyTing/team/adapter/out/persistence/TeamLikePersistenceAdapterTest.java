package com.everyTing.team.adapter.out.persistence;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.from;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.everyTing.core.domain.Gender;
import com.everyTing.team.adapter.out.persistence.entity.TeamEntity;
import com.everyTing.team.adapter.out.persistence.entity.TeamLikeEntity;
import com.everyTing.team.adapter.out.persistence.entity.TeamMemberEntity;
import com.everyTing.team.adapter.out.persistence.repository.TeamEntityRepository;
import com.everyTing.team.adapter.out.persistence.repository.TeamLikeEntityRepository;
import com.everyTing.team.adapter.out.persistence.repository.TeamMemberEntityRepository;
import com.everyTing.team.utils.BaseTest;
import com.everyTing.team.utils.TeamEntityFixture;
import com.everyTing.team.utils.TeamMemberEntityFixture;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

@DisplayName("팀 좋아요 - persistent adapter test")
@ExtendWith(MockitoExtension.class)
class TeamLikePersistenceAdapterTest extends BaseTest {

    TeamMemberEntity fromTeamMember = TeamMemberEntityFixture.getList().get(1);
    TeamEntity fromTeam = TeamEntityFixture.get(1L);
    TeamEntity toTeam = TeamEntityFixture.get(1L + 1);
    TeamLikeEntity teamLike = TeamLikeEntity.of(fromTeamMember, fromTeam, toTeam);

    @InjectMocks
    private TeamLikePersistenceAdapter sut;
    @Mock
    private TeamLikeEntityRepository teamLikeEntityRepository;
    @Mock
    private TeamEntityRepository teamEntityRepository;
    @Mock
    private TeamMemberEntityRepository teamMemberEntityRepository;

    @DisplayName("팀 좋아요")
    @Test
    void saveTeamLike() {
        ReflectionTestUtils.setField(teamLike, "id", 1L);
        ReflectionTestUtils.setField(toTeam, "gender", Gender.MALE);

        // given
        given(teamEntityRepository.findById(any())).willReturn(
            Optional.of(toTeam)).willReturn(Optional.of(fromTeam));
        given(teamMemberEntityRepository.findByTeamIdAndMemberId(any(), any()))
            .willReturn(Optional.of(fromTeamMember));
        given(teamLikeEntityRepository.save(any()))
            .willReturn(teamLike);

        // when
        Long created = sut.saveTeamLike(toTeam.getId(), fromTeamMember.getTeamId(),
            fromTeamMember.getMemberId());

        // then
        assertThat(created).isEqualTo(1L);
    }
}