package com.everyTing.team.utils;

import com.everyTing.core.domain.Gender;
import com.everyTing.team.adapter.out.persistence.entity.TeamEntity;
import com.everyTing.team.adapter.out.persistence.entity.TeamRequestEntity;
import com.everyTing.team.adapter.out.persistence.entity.data.MemberLimit;
import java.time.LocalDateTime;
import org.springframework.test.util.ReflectionTestUtils;

public class TeamRequestEntityFixture {

    public static TeamEntity getFromTeam() {
        TeamEntity womenTeam = TeamEntityFixture.get(1L);
        ReflectionTestUtils.setField(womenTeam, "gender", Gender.FEMALE);
        ReflectionTestUtils.setField(womenTeam, "memberLimit", MemberLimit.from((short) 3));
        ReflectionTestUtils.setField(womenTeam, "memberNumber", (short) 3);

        return womenTeam;
    }

    public static TeamEntity getToTeam() {
        TeamEntity menTeam = TeamEntityFixture.get(2L);
        ReflectionTestUtils.setField(menTeam, "gender", Gender.MALE);
        ReflectionTestUtils.setField(menTeam, "memberLimit", MemberLimit.from((short) 3));
        ReflectionTestUtils.setField(menTeam, "memberNumber", (short) 3);

        return menTeam;
    }

    public static TeamRequestEntity get() {
        TeamEntity womenTeam = getFromTeam();
        TeamEntity menTeam = getToTeam();
        TeamRequestEntity entity = TeamRequestEntity.of(womenTeam.getId(), menTeam.getId());
        ReflectionTestUtils.setField(entity, "id", 1L);
        ReflectionTestUtils.setField(entity, "createdAt", LocalDateTime.now());
        return entity;
    }
}
