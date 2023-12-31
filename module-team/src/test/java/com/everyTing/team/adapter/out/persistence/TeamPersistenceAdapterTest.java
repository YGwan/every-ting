package com.everyTing.team.adapter.out.persistence;

import static com.everyTing.team.utils.TeamEntityFixture.code;
import static com.everyTing.team.utils.TeamEntityFixture.gender;
import static com.everyTing.team.utils.TeamEntityFixture.major;
import static com.everyTing.team.utils.TeamEntityFixture.memberLimit;
import static com.everyTing.team.utils.TeamEntityFixture.name;
import static com.everyTing.team.utils.TeamEntityFixture.university;
import static com.everyTing.team.utils.TeamHashtagEntityFixture.hashtags;
import static com.everyTing.team.utils.TeamRegionEntityFixture.regions;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;

import com.everyTing.team.adapter.out.persistence.entity.TeamEntity;
import com.everyTing.team.adapter.out.persistence.repository.TeamEntityRepository;
import com.everyTing.team.adapter.out.persistence.repository.TeamHashtagEntityJdbcRepository;
import com.everyTing.team.adapter.out.persistence.repository.TeamRegionEntityJdbcRepository;
import com.everyTing.team.domain.Team;
import com.everyTing.team.utils.BaseTest;
import com.everyTing.team.utils.TeamEntityFixture;
import java.util.Optional;
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
    private TeamHashtagEntityJdbcRepository teamHashtagEntityJdbcRepository;
    @Mock
    private TeamRegionEntityJdbcRepository teamRegionEntityJdbcRepository;

    @DisplayName("id로 팀 조회")
    @Test
    void findTeamById() {
        // given
        given(teamEntityRepository.findById(any())).willReturn(Optional.of(teamEntity));

        // when
        Team team = sut.findTeamById(1L);

        // then
        assertThat(team.getId()).isEqualTo(teamEntity.getId());
    }

    @DisplayName("code로 팀 조회")
    @Test
    void findTeamByCode() {
        // given
        given(teamEntityRepository.findByCode(any())).willReturn(Optional.of(teamEntity));

        // when
        Team team = sut.findTeamByCode(code);

        // then
        assertThat(team.getCode()).isEqualTo(teamEntity.getCode());
    }


    @DisplayName("팀 생성")
    @Test
    void saveTeam() {
        // given
        given(teamEntityRepository.save(any())).willReturn(teamEntity);
        willDoNothing().given(teamHashtagEntityJdbcRepository)
                       .saveAll(any());
        willDoNothing().given(teamRegionEntityJdbcRepository)
                       .saveAll(any());

        // when
        Long createdTeamId = sut.saveTeam(
            100L,
            name,
            regions,
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
