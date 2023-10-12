package com.everyTing.team.adapter.in.web;

import com.everyTing.core.dto.Response;
import com.everyTing.team.adapter.in.web.docs.TeamMemberControllerDocs;
import com.everyTing.team.application.port.in.TeamMemberUseCase;
import com.everyTing.team.application.port.in.command.TeamMemberFindCommand;
import com.everyTing.team.domain.TeamMembers;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeamMemberController implements TeamMemberControllerDocs {

    private final TeamMemberUseCase teamMemberUseCase;

    public TeamMemberController(TeamMemberUseCase teamMemberUseCase) {
        this.teamMemberUseCase = teamMemberUseCase;
    }

    public Response<TeamMembers> memberList(Long teamId) {
        TeamMemberFindCommand command = TeamMemberFindCommand.of(teamId);
        return Response.success(teamMemberUseCase.findTeamMembers(command));
    }
}
