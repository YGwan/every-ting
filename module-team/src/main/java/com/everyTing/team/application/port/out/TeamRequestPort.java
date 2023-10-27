package com.everyTing.team.application.port.out;

import com.everyTing.team.domain.TeamRequest;
import com.everyTing.team.domain.TeamRequests;

public interface TeamRequestPort {

    TeamRequests findTeamRequest(Long fromTeamId, Long toTeamId);

    TeamRequest findTeamRequest(Long requestId);

    Long countTodayRequest(Long fromTeamId);

    Long saveTeamRequest(Long fromTeamId, Long toTeamId);

    void removeTeamRequest(Long fromTeamId, Long toTeamId);

    void removeTeamRequestsBetweenTeams(Long teamId1, Long teamId2);
}
