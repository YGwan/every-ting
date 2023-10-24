package com.everyTing.team.adapter.out.persistence;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.everyTing.team.adapter.out.persistence.entity.TeamDateEntity;
import com.everyTing.team.adapter.out.persistence.repository.TeamDateEntityRepository;
import com.everyTing.team.adapter.out.persistence.repository.TeamEntityRepository;
import com.everyTing.team.utils.BaseTest;
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
        TeamDateEntity expected = TeamDateEntity.of(1L, 2L);
        ReflectionTestUtils.setField(expected, "id", 1L);

        // given
        given(teamDateEntityRepository.save(any())).willReturn(expected);

        // when
        Long createdId = sut.saveTeamDate(1L, 2L);

        // then
        assertThat(createdId).isEqualTo(expected.getId());
    }
}