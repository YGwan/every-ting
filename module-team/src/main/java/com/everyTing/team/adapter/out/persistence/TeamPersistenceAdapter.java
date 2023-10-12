package com.everyTing.team.adapter.out.persistence;

import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_006;

import com.everyTing.core.domain.Gender;
import com.everyTing.core.exception.TingApplicationException;
import com.everyTing.team.adapter.out.persistence.entity.TeamEntity;
import com.everyTing.team.adapter.out.persistence.entity.TeamHashtagEntity;
import com.everyTing.team.adapter.out.persistence.entity.TeamRegionEntity;
import com.everyTing.team.adapter.out.persistence.entity.data.Code;
import com.everyTing.team.adapter.out.persistence.entity.data.Hashtag;
import com.everyTing.team.adapter.out.persistence.entity.data.Major;
import com.everyTing.team.adapter.out.persistence.entity.data.MemberLimit;
import com.everyTing.team.adapter.out.persistence.entity.data.Name;
import com.everyTing.team.adapter.out.persistence.entity.data.Region;
import com.everyTing.team.adapter.out.persistence.entity.data.University;
import com.everyTing.team.adapter.out.persistence.repository.TeamEntityRepository;
import com.everyTing.team.adapter.out.persistence.repository.TeamHashtagEntityJdbcRepository;
import com.everyTing.team.adapter.out.persistence.repository.TeamRegionEntityJdbcRepository;
import com.everyTing.team.application.port.out.TeamPort;
import com.everyTing.team.domain.Team;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

@Repository
public class TeamPersistenceAdapter implements TeamPort {

    private final TeamEntityRepository teamEntityRepository;
    private final TeamHashtagEntityJdbcRepository teamHashtagEntityJdbcRepository;
    private final TeamRegionEntityJdbcRepository teamRegionEntityJdbcRepository;

    public TeamPersistenceAdapter(TeamEntityRepository teamEntityRepository,
        TeamHashtagEntityJdbcRepository teamHashtagEntityJdbcRepository,
        TeamRegionEntityJdbcRepository teamRegionEntityJdbcRepository) {
        this.teamEntityRepository = teamEntityRepository;
        this.teamHashtagEntityJdbcRepository = teamHashtagEntityJdbcRepository;
        this.teamRegionEntityJdbcRepository = teamRegionEntityJdbcRepository;
    }

    @Override
    public Team findTeamById(Long teamId) {
        final TeamEntity team =
            teamEntityRepository.findById(teamId)
                                .orElseThrow(() -> new TingApplicationException(TEAM_006));

        return Team.from(team);
    }

    @Override
    public Team findTeamByCode(Code code) {
        final TeamEntity team =
            teamEntityRepository.findByCode(code)
                                .orElseThrow(() -> new TingApplicationException(TEAM_006));

        return Team.from(team);
    }

    @Override
    public Long saveTeam(Long memberId, Name name, List<Region> regions, University university,
        Major major, Code code, MemberLimit memberLimit, Gender gender, List<Hashtag> hashtags) {

        TeamEntity pre = TeamEntity.of(name, university, major, code, memberLimit, gender);

        final TeamEntity created = teamEntityRepository.save(pre);

        saveTeamHashtags(created, hashtags);
        saveTeamRegions(created, regions);

        return created.getId();
    }

    private void saveTeamHashtags(TeamEntity teamEntity, List<Hashtag> hashtags) {
        List<TeamHashtagEntity> teamHashtags = hashtags.stream()
                                                       .map(hashtag -> TeamHashtagEntity.of(
                                                           teamEntity, hashtag))
                                                       .collect(Collectors.toList());
        teamHashtagEntityJdbcRepository.saveAll(teamHashtags);
    }

    private void saveTeamRegions(TeamEntity teamEntity, List<Region> regions) {
        List<TeamRegionEntity> teamRegions = regions.stream()
                                                    .map(region -> TeamRegionEntity.of(
                                                        teamEntity, region))
                                                    .collect(Collectors.toList());
        teamRegionEntityJdbcRepository.saveAll(teamRegions);
    }
}
