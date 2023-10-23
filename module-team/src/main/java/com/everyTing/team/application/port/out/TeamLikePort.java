package com.everyTing.team.application.port.out;

import com.everyTing.team.domain.TeamLikes;

public interface TeamLikePort {

    TeamLikes findTeamLikeByFromTeamIdAndToTeamId(Long fromTeamId, Long toTeamId);

    Long saveTeamLike(Long toTeamId, Long fromTeamId, Long fromMemberId);

    void removeTeamLike(Long toTeamId, Long fromTeamId, Long fromMemberId);
}
