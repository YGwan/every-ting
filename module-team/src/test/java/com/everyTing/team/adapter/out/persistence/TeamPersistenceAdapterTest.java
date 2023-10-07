package com.everyTing.team.adapter.out.persistence;

import static com.everyTing.team.utils.TeamEntityFixture.code;
import static com.everyTing.team.utils.TeamEntityFixture.gender;
import static com.everyTing.team.utils.TeamEntityFixture.major;
import static com.everyTing.team.utils.TeamEntityFixture.memberLimit;
import static com.everyTing.team.utils.TeamEntityFixture.name;
import static com.everyTing.team.utils.TeamEntityFixture.region;
import static com.everyTing.team.utils.TeamEntityFixture.university;
import static com.everyTing.team.utils.TeamHashtagEntityFixture.hashtags;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.mock;

import com.everyTing.team.adapter.out.persistence.entity.TeamEntity;
import com.everyTing.team.adapter.out.persistence.entity.TeamMemberEntity;
import com.everyTing.team.adapter.out.persistence.repository.TeamEntityRepository;
import com.everyTing.team.adapter.out.persistence.repository.TeamHashtagEntityJdbcRepository;
import com.everyTing.team.adapter.out.persistence.repository.TeamMemberEntityRepository;
import com.everyTing.team.utils.BaseTest;
import com.everyTing.team.utils.TeamEntityFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("팀 - persistent adapter test")
@ExtendWith(MockitoExtension.class)
class TeamPersistenceAdapterTest extends BaseTest {

    private final TeamEntity teamEntity = TeamEntityFixture.get(1L);

    @InjectMocks
    private TeamPersistenceAdapter sut;
    @Mock
    private TeamEntityRepository teamEntityRepository;
    @Mock
    private TeamMemberEntityRepository teamMemberEntityRepository;
    @Mock
    private TeamHashtagEntityJdbcRepository teamHashtagEntityJdbcRepository;

    @DisplayName("팀 생성")
    @Test
    void saveTeam() {
        // given
        given(teamEntityRepository.save(any())).willReturn(teamEntity);
        willDoNothing().given(teamHashtagEntityJdbcRepository).saveAll(any());

        // when
        Long createdTeamId = sut.saveTeam(
            100L,
            name,
            region,
            university,
            major,
            code,
            memberLimit,
            gender,
            hashtags);

        // then
        assertThat(createdTeamId).isEqualTo(teamEntity.getId());
    }
}