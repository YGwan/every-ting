package com.everyTing.team.adapter.in.web;

import com.everyTing.core.dto.Response;
import com.everyTing.team.adapter.in.web.docs.TeamControllerDocs;
import com.everyTing.team.adapter.in.web.request.TeamSaveRequest;
import com.everyTing.team.application.port.in.TeamUseCase;
import com.everyTing.team.application.port.in.command.TeamFindByCodeCommand;
import com.everyTing.team.application.port.in.command.TeamFindByIdCommand;
import com.everyTing.team.application.port.in.command.TeamSaveCommand;
import com.everyTing.team.domain.Team;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeamController implements TeamControllerDocs {

    private final Long mockMemberId = 100L;

    private final TeamUseCase teamUseCase;

    public TeamController(TeamUseCase teamUseCase) {
        this.teamUseCase = teamUseCase;
    }

    public Response<Team> teamDetails(Long teamId) {
        return Response.success(teamUseCase.findTeamById(TeamFindByIdCommand.of(teamId)));
    }

    public Response<Team> teamDetails(String teamCode) {
        return Response.success(teamUseCase.findTeamByCode(TeamFindByCodeCommand.of(teamCode)));
    }

    public Response<Long> teamSave(TeamSaveRequest request) {
        TeamSaveCommand command = TeamSaveCommand.of(mockMemberId, request.getName(),
            request.getMemberLimit(), request.getRegions(), request.getHashtags());

        return Response.success(teamUseCase.saveTeam(command));
    }
}
