package com.everyTing.team.adapter.out.persistence;

import com.everyTing.team.adapter.out.persistence.entity.TeamEntity;
import com.everyTing.team.adapter.out.persistence.entity.TeamHashtagEntity;
import com.everyTing.team.adapter.out.persistence.entity.data.Hashtag;
import com.everyTing.team.adapter.out.persistence.repository.TeamEntityRepository;
import com.everyTing.team.adapter.out.persistence.repository.TeamHashtagEntityJdbcRepository;
import com.everyTing.team.adapter.out.persistence.repository.TeamHashtagEntityRepository;
import com.everyTing.team.application.port.out.TeamHashtagPort;
import com.everyTing.team.domain.TeamHashtags;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

@Repository
public class TeamHashtagPersistenceAdapter implements TeamHashtagPort {

    private final TeamEntityRepository teamEntityRepository;
    private final TeamHashtagEntityRepository teamHashtagEntityRepository;
    private final TeamHashtagEntityJdbcRepository teamHashtagEntityJdbcRepository;

    public TeamHashtagPersistenceAdapter(TeamEntityRepository teamEntityRepository,
        TeamHashtagEntityRepository teamHashtagEntityRepository,
        TeamHashtagEntityJdbcRepository teamHashtagEntityJdbcRepository) {
        this.teamEntityRepository = teamEntityRepository;
        this.teamHashtagEntityRepository = teamHashtagEntityRepository;
        this.teamHashtagEntityJdbcRepository = teamHashtagEntityJdbcRepository;
    }

    @Override
    public TeamHashtags findHashtags(Long teamId) {
        final List<TeamHashtagEntity> hashtagEntities =
            teamHashtagEntityRepository.findByTeamIdOrderByCreatedAt(teamId);

        return TeamHashtags.from(hashtagEntities);
    }

    @Override
    public void removeHashtags(List<Long> hashtagIds) {
        teamHashtagEntityRepository.deleteAllByIdIn(hashtagIds);
    }

    @Override
    public void saveHashtags(Long teamId, List<Hashtag> hashtags) {
        TeamEntity teamEntity = teamEntityRepository.getReferenceById(teamId);
        List<TeamHashtagEntity> teamHashtags =
            hashtags.stream()
                    .map(hashtag -> TeamHashtagEntity.of(teamEntity, hashtag))
                    .collect(Collectors.toList());
        teamHashtagEntityJdbcRepository.saveAll(teamHashtags);
    }
}
