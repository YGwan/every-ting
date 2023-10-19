package com.everyTing.team.adapter.out.persistence;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.everyTing.team.adapter.out.persistence.entity.TeamRequestEntity;
import com.everyTing.team.adapter.out.persistence.repository.TeamEntityRepository;
import com.everyTing.team.adapter.out.persistence.repository.TeamRequestEntityRepository;
import com.everyTing.team.utils.BaseTest;
import com.everyTing.team.utils.TeamRequestEntityFixture;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("과팅 요청 - persistent adapter test")
@ExtendWith(MockitoExtension.class)
class TeamRequestPersistenceAdapterTest extends BaseTest {

    @InjectMocks
    private TeamRequestPersistenceAdapter sut;
    @Mock
    private TeamRequestEntityRepository teamRequestEntityRepository;
    @Mock
    private TeamEntityRepository teamEntityRepository;

    @DisplayName("요청")
    @Test
    void saveTeamRequest() {
        TeamRequestEntity fixture = TeamRequestEntityFixture.get();

        // given
        given(teamRequestEntityRepository.existsByFromTeamIdAndToTeamId(any(), any())).willReturn(
            false);
        given(teamEntityRepository.findById(any())).willReturn(Optional.of(fixture.getFromTeam()))
                                                   .willReturn(Optional.of(fixture.getToTeam()));
        given(teamRequestEntityRepository.save(any())).willReturn(fixture);

        // when
        Long created = sut.saveTeamRequest(1L, 2L);

        // then
        assertThat(created).isEqualTo(fixture.getId());

    }
}