package com.everyTing.team.application.port.out;

public interface TeamLikePort {

    Long saveTeamLike(Long toTeamId, Long fromTeamId, Long fromMemberId);

    void removeTeamLike(Long toTeamId, Long fromTeamId, Long fromMemberId);
}
