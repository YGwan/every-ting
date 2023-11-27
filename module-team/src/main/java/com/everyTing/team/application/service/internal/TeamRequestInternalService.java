package com.everyTing.team.application.service.internal;

import com.everyTing.team.application.port.out.TeamRequestPort;
import com.everyTing.team.domain.TeamRequest;
import org.springframework.stereotype.Service;

@Service
public class TeamRequestInternalService {

    private final TeamRequestPort teamRequestPort;

    public TeamRequestInternalService(TeamRequestPort teamRequestPort) {
        this.teamRequestPort = teamRequestPort;
    }

    public void removeTeamRequest(TeamRequest request) {
        teamRequestPort.removeTeamRequest(request.getId());
    }

    public void rejectTeamRequest(TeamRequest request) {
        teamRequestPort.removeTeamRequest(request.getId());
    }
}
