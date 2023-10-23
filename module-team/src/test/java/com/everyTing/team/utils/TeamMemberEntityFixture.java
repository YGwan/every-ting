package com.everyTing.team.utils;

import com.everyTing.team.adapter.out.persistence.entity.TeamMemberEntity;
import com.everyTing.team.adapter.out.persistence.entity.data.Role;
import java.util.List;
import org.springframework.test.util.ReflectionTestUtils;

public class TeamMemberEntityFixture {

    public static List<TeamMemberEntity> getList() {
        TeamMemberEntity teamLeaderEntity = TeamMemberEntity.of(1L, 1L, Role.LEADER);
        ReflectionTestUtils.setField(teamLeaderEntity, "id", 1L);

        TeamMemberEntity teamMemberEntity = TeamMemberEntity.of(1L, 2L, Role.MEMBER);
        ReflectionTestUtils.setField(teamMemberEntity, "id", 2L);
        return List.of(teamLeaderEntity, teamMemberEntity);
    }

    public static TeamMemberEntity get(Long teamMemberId) {
        TeamMemberEntity teamMemberEntity = getList().get(0);
        ReflectionTestUtils.setField(teamMemberEntity, "id", teamMemberId);
        return teamMemberEntity;
    }
}
