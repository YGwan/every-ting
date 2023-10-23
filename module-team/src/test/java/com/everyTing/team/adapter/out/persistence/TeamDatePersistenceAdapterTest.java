package com.everyTing.team.adapter.out.persistence;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.everyTing.team.adapter.out.persistence.entity.TeamDateEntity;
import com.everyTing.team.adapter.out.persistence.entity.TeamRequestEntity;
import com.everyTing.team.adapter.out.persistence.repository.TeamDateEntityRepository;
import com.everyTing.team.adapter.out.persistence.repository.TeamEntityRepository;
import com.everyTing.team.utils.BaseTest;
import com.everyTing.team.utils.TeamRequestEntityFixture;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

@DisplayName("팀 데이트 - persistent adapter test")
@ExtendWith(MockitoExtension.class)
class TeamDatePersistenceAdapterTest extends BaseTest {

    @InjectMocks
    private TeamDatePersistenceAdapter sut;
    @Mock
    TeamDateEntityRepository teamDateEntityRepository;
    @Mock
    TeamEntityRepository teamEntityRepository;

    @DisplayName("팀 매칭")
    @Test
    void saveTeamDate() {
        TeamRequestEntity fixture = TeamRequestEntityFixture.get();
        TeamDateEntity expected = TeamDateEntity.of(fixture.getFromTeam(), fixture.getToTeam());
        ReflectionTestUtils.setField(expected, "id", 1L);

        // given
        given(teamEntityRepository.findById(any())).willReturn(Optional.of(fixture.getFromTeam()))
                                                   .willReturn(Optional.of(fixture.getToTeam()));
        given(teamDateEntityRepository.existsByWomenTeamAndMenTeam(any(), any())).willReturn(false);
        given(teamDateEntityRepository.save(any())).willReturn(expected);

        // when
        Long createdId = sut.saveTeamDate(fixture.getFromTeam()
                                                 .getId(), fixture.getToTeam()
                                                                  .getId());

        // then
        assertThat(createdId).isEqualTo(expected.getId());
    }
}