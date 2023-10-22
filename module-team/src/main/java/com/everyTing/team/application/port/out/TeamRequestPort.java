package com.everyTing.team.application.port.out;

import com.everyTing.team.domain.TeamRequests;

public interface TeamRequestPort {

    Long countTodayRequest(Long fromTeamId);

    Long saveTeamRequest(Long fromTeamId, Long toTeamId);

    TeamRequests findTeamRequest(Long fromTeamId, Long toTeamId);
}
