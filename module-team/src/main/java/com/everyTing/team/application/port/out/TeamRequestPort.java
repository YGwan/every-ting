package com.everyTing.team.application.port.out;

import com.everyTing.team.domain.TeamRequest;
import com.everyTing.team.domain.TeamRequests;
import java.time.LocalDateTime;
import java.util.List;

public interface TeamRequestPort {

    TeamRequests findTeamRequests(Long fromTeamId, Long toTeamId);

    TeamRequests findTeamRequestsByFromTeamIdIn(List<Long> fromTeamId);

    TeamRequest findTeamRequest(Long requestId);

    Long saveTeamRequest(Long fromTeamId, Long toTeamId);

    void removeTeamRequest(Long requestId);

    void removeTeamRequestsBetweenTeams(Long teamId1, Long teamId2);

    Boolean existsTeamRequest(Long fromTeamId, Long toTeamId);

    Long countByFromTeamIdAndCreatedAtAfter(Long fromTeamId, LocalDateTime now);
}
