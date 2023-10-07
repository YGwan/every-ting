package com.everyTing.team.utils;


import static java.time.LocalDateTime.now;

import com.everyTing.core.domain.Gender;
import com.everyTing.team.adapter.out.persistence.entity.TeamEntity;
import com.everyTing.team.adapter.out.persistence.entity.data.Code;
import com.everyTing.team.adapter.out.persistence.entity.data.Major;
import com.everyTing.team.adapter.out.persistence.entity.data.MemberLimit;
import com.everyTing.team.adapter.out.persistence.entity.data.Name;
import com.everyTing.team.adapter.out.persistence.entity.data.Region;
import com.everyTing.team.adapter.out.persistence.entity.data.University;
import java.time.LocalDateTime;
import org.springframework.test.util.ReflectionTestUtils;

public class TeamEntityFixture {

    public static final Name name = Name.from("여기 여기 모여라");
    public static final Region region = Region.from("경기 남부");
    public static final University university = University.from("단국대학교");
    public static final Major major = Major.from("컴퓨터공학과");
    public static final Code code = Code.from("asegasegaes");
    public static final MemberLimit memberLimit = MemberLimit.from((short) 3);
    public static final Gender gender = Gender.FEMALE;
    public static final LocalDateTime createdAt = now();
    public static final LocalDateTime updatedAt = now();

    public static TeamEntity get(Long teamId) {
        TeamEntity team = TeamEntity.of(name, region, university, major, code, memberLimit, gender);
        ReflectionTestUtils.setField(team, "id", teamId);
        ReflectionTestUtils.setField(team, "createdAt", createdAt);
        ReflectionTestUtils.setField(team, "updatedAt", updatedAt);
        return team;
    }
}