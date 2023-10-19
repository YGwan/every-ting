package com.everyTing.team.adapter.out.persistence.repository;

import com.everyTing.team.adapter.out.persistence.entity.QTeamEntity;
import com.everyTing.team.adapter.out.persistence.entity.QTeamLikeEntity;
import com.everyTing.team.adapter.out.persistence.entity.TeamEntity;
import com.everyTing.team.adapter.out.persistence.projection.TeamEntityWithLikeCount;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

@Repository
public class TeamEntityQueryRepository {

    private final JPAQueryFactory queryFactory;

    public TeamEntityQueryRepository(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    public Slice<TeamEntityWithLikeCount> findOtherTeams(TeamEntity myTeam, Pageable pageable) {
        QTeamEntity teamEntity = QTeamEntity.teamEntity;
        QTeamLikeEntity teamLikeEntity = QTeamLikeEntity.teamLikeEntity;

        List<TeamEntityWithLikeCount> teamsWithLikeCount = queryFactory
            .select(
                Projections.constructor(TeamEntityWithLikeCount.class, teamEntity, teamLikeEntity.count()))
            .from(teamEntity)
            .leftJoin(teamLikeEntity)
            .on(teamEntity.eq(teamLikeEntity.toTeam)
                          .and(teamLikeEntity.fromTeam.eq(myTeam)))
            .where(teamEntity.gender.ne(myTeam.getGender())
                                    .and(teamEntity.memberLimit.value.eq(myTeam.getMemberLimit()))
                                    .and(teamEntity.memberLimit.value.eq(teamEntity.memberNumber)))
            .groupBy(teamEntity)
            .orderBy(teamLikeEntity.count().desc(), teamEntity.createdAt.asc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize() + 1)
            .fetch();

        return convertToSlice(teamsWithLikeCount, pageable);
    }

    private Slice<TeamEntityWithLikeCount> convertToSlice(List<TeamEntityWithLikeCount> results,
        Pageable pageable) {
        boolean hasNext = false;
        if (results.size() > pageable.getPageSize()) {
            hasNext = true;
            results.remove(pageable.getPageSize());
        }
        return new SliceImpl<>(results, pageable, hasNext);
    }
}
