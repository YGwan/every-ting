package com.everyTing.team.adapter.out.persistence.repository;

import com.everyTing.team.adapter.out.persistence.entity.QTeamRequestEntity;
import com.everyTing.team.adapter.out.persistence.entity.TeamRequestEntity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class TeamRequestEntityQueryRepository {

    private final JPAQueryFactory queryFactory;

    public TeamRequestEntityQueryRepository(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    public List<TeamRequestEntity> findAllByFromTeamIdAndToTeamId(Long fromTeamId, Long toTeamId) {
        QTeamRequestEntity teamRequestEntity = QTeamRequestEntity.teamRequestEntity;

        JPAQuery<TeamRequestEntity> query = queryFactory.selectFrom(teamRequestEntity);

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(fromTeamIdEq(teamRequestEntity, fromTeamId))
               .and(toTeamIdEq(teamRequestEntity, toTeamId));

        return query.where(builder)
                    .orderBy(teamRequestEntity.createdAt.asc())
                    .fetch();
    }

    private Predicate fromTeamIdEq(QTeamRequestEntity qTeamRequestEntity, Long fromTeamId) {
        return fromTeamId != null ? qTeamRequestEntity.fromTeam.id.eq(fromTeamId) : null;
    }

    private Predicate toTeamIdEq(QTeamRequestEntity qTeamRequestEntity, Long toTeamId) {
        return toTeamId != null ? qTeamRequestEntity.toTeam.id.eq(toTeamId) : null;
    }
}
