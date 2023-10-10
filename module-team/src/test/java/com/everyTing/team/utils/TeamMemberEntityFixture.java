package com.everyTing.team.utils;

import com.everyTing.team.adapter.out.persistence.entity.TeamMemberEntity;
import com.everyTing.team.adapter.out.persistence.entity.data.Role;
import java.util.List;

public class TeamMemberEntityFixture {

    public static List<TeamMemberEntity> get() {
        return List.of(
            TeamMemberEntity.of(1L, 1L, Role.LEADER),
            TeamMemberEntity.of(1L, 2L, Role.MEMBER));
    }
}
