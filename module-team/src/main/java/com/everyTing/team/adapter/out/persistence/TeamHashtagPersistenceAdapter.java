package com.everyTing.team.adapter.out.persistence;

import com.everyTing.team.adapter.out.persistence.entity.TeamHashtagEntity;
import com.everyTing.team.adapter.out.persistence.repository.TeamHashtagEntityRepository;
import com.everyTing.team.application.port.out.TeamHashtagPort;
import com.everyTing.team.domain.TeamHashtags;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class TeamHashtagPersistenceAdapter implements TeamHashtagPort {

    private final TeamHashtagEntityRepository teamHashtagEntityRepository;

    public TeamHashtagPersistenceAdapter(
        TeamHashtagEntityRepository teamHashtagEntityRepository) {
        this.teamHashtagEntityRepository = teamHashtagEntityRepository;
    }

    @Override
    public TeamHashtags findHashtags(Long teamId) {
        final List<TeamHashtagEntity> hashtagEntities =
            teamHashtagEntityRepository.findByTeamIdOrderByCreatedAt(teamId);

        return TeamHashtags.from(hashtagEntities);
    }
}
