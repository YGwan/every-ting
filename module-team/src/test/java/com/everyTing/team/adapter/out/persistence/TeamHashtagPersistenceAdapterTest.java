package com.everyTing.team.adapter.out.persistence;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.everyTing.team.adapter.out.persistence.entity.TeamHashtagEntity;
import com.everyTing.team.adapter.out.persistence.repository.TeamHashtagEntityRepository;
import com.everyTing.team.domain.TeamHashtag;
import com.everyTing.team.domain.TeamHashtags;
import com.everyTing.team.utils.BaseTest;
import com.everyTing.team.utils.TeamHashtagEntityFixture;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("팀 해시태그 - persistent adapter test")
@ExtendWith(MockitoExtension.class)
class TeamHashtagPersistenceAdapterTest extends BaseTest {

    private final List<TeamHashtagEntity> teamHashtagEntities = TeamHashtagEntityFixture.get();

    @InjectMocks
    private TeamHashtagPersistenceAdapter sut;
    @Mock
    private TeamHashtagEntityRepository teamHashtagEntityRepository;

    @DisplayName("팀 해시태그 조회")
    @Test
    void findHashtags() {
        // given
        given(teamHashtagEntityRepository.findByTeamIdOrderByCreatedAt(any())).willReturn(
            teamHashtagEntities);

        // when
        TeamHashtags hashtag = sut.findHashtags(1L);

        // then
        assertThat(hashtag.getHashtags()
                          .stream()
                          .map(TeamHashtag::getContent)
                          .collect(Collectors.toUnmodifiableList()))
            .contains(teamHashtagEntities.get(0)
                                         .getContent());
    }
}