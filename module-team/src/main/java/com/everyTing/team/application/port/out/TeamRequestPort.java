package com.everyTing.team.application.port.out;

import com.everyTing.team.domain.TeamRequest;
import com.everyTing.team.domain.TeamRequests;
import java.time.LocalDateTime;

public interface TeamRequestPort {

    TeamRequests findTeamRequest(Long fromTeamId, Long toTeamId);

    TeamRequest findTeamRequest(Long requestId);

    Long saveTeamRequest(Long fromTeamId, Long toTeamId);

    void removeTeamRequest(Long fromTeamId, Long toTeamId);

    void removeTeamRequestsBetweenTeams(Long teamId1, Long teamId2);

    Boolean existsTeamRequest(Long fromTeamId, Long toTeamId);

    Long countByFromTeamIdAndCreatedAtAfter(Long fromTeamId, LocalDateTime now);
}
