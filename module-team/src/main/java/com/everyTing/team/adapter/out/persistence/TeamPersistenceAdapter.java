package com.everyTing.team.adapter.out.persistence;

import com.everyTing.core.domain.Gender;
import com.everyTing.team.adapter.out.persistence.entity.TeamEntity;
import com.everyTing.team.adapter.out.persistence.entity.TeamHashtagEntity;
import com.everyTing.team.adapter.out.persistence.entity.data.Code;
import com.everyTing.team.adapter.out.persistence.entity.data.Hashtag;
import com.everyTing.team.adapter.out.persistence.entity.data.Major;
import com.everyTing.team.adapter.out.persistence.entity.data.MemberLimit;
import com.everyTing.team.adapter.out.persistence.entity.data.Name;
import com.everyTing.team.adapter.out.persistence.entity.data.Region;
import com.everyTing.team.adapter.out.persistence.entity.data.University;
import com.everyTing.team.adapter.out.persistence.repository.TeamEntityRepository;
import com.everyTing.team.adapter.out.persistence.repository.TeamHashtagEntityJdbcRepository;
import com.everyTing.team.adapter.out.persistence.repository.TeamMemberEntityRepository;
import com.everyTing.team.application.port.out.TeamPort;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

@Repository
public class TeamPersistenceAdapter implements TeamPort {

    private final TeamEntityRepository teamEntityRepository;
    private final TeamMemberEntityRepository teamMemberEntityRepository;
    private final TeamHashtagEntityJdbcRepository teamHashtagEntityJdbcRepository;

    public TeamPersistenceAdapter(TeamEntityRepository teamEntityRepository,
        TeamMemberEntityRepository teamMemberEntityRepository,
        TeamHashtagEntityJdbcRepository teamHashtagEntityJdbcRepository) {
        this.teamEntityRepository = teamEntityRepository;
        this.teamMemberEntityRepository = teamMemberEntityRepository;
        this.teamHashtagEntityJdbcRepository = teamHashtagEntityJdbcRepository;
    }

    @Override
    public Long saveTeam(Long memberId, Name name, Region region, University university,
        Major major, Code code, MemberLimit memberLimit, Gender gender, List<Hashtag> hashtags) {

        TeamEntity pre = TeamEntity.of(name, region, university, major, code, memberLimit, gender);

        final TeamEntity created = teamEntityRepository.save(pre);

        teamHashtagEntityJdbcRepository.saveAll(hashtags.stream()
                                                        .map(
                                                            hashtag -> TeamHashtagEntity.of(created,
                                                                hashtag))
                                                        .collect(Collectors.toList()));

        return created.getId();
    }
}

