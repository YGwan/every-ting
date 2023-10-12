package com.everyTing.team.utils;

import com.everyTing.team.adapter.out.persistence.entity.TeamMemberEntity;
import com.everyTing.team.adapter.out.persistence.entity.data.Role;
import java.util.List;
import org.springframework.test.util.ReflectionTestUtils;

public class TeamMemberEntityFixture {

    public static List<TeamMemberEntity> getList() {
        return List.of(
            TeamMemberEntity.of(1L, 1L, Role.LEADER),
            TeamMemberEntity.of(1L, 2L, Role.MEMBER));
    }

    public static TeamMemberEntity get(Long teamMemberId) {
        TeamMemberEntity teamMemberEntity = getList().get(0);
        ReflectionTestUtils.setField(teamMemberEntity, "id", teamMemberId);
        return teamMemberEntity;
    }
}
