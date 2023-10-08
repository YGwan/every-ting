package com.everyTing.team.application.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import com.everyTing.team.adapter.out.persistence.entity.TeamHashtagEntity;
import com.everyTing.team.application.port.in.command.TeamHashtagFindCommand;
import com.everyTing.team.application.port.out.TeamHashtagPort;
import com.everyTing.team.domain.TeamHashtags;
import com.everyTing.team.utils.BaseTest;
import com.everyTing.team.utils.TeamHashtagEntityFixture;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("팀 해시태그 service test")
@ExtendWith(MockitoExtension.class)
class TeamHashtagServiceTest extends BaseTest {

    private final List<TeamHashtagEntity> hashtagEntityList = TeamHashtagEntityFixture.get();

    @InjectMocks
    private TeamHashtagService sut;
    @Mock
    private TeamHashtagPort teamHashtagPort;

    @DisplayName("팀 해시태그 조회")
    @Test
    void findHashtags() {
        TeamHashtagFindCommand command = TeamHashtagFindCommand.of(1L);

        // given
        given(teamHashtagPort.findHashtags(command.getTeamId())).willReturn(
            TeamHashtags.from(hashtagEntityList));

        // when
        TeamHashtags teamHashtags = sut.findHashtags(command);

        // then
        assertThat(teamHashtags.getHashtags()
                               .size()).isEqualTo(hashtagEntityList.size());
    }
}
