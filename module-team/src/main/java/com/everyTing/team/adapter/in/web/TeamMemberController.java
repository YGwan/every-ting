package com.everyTing.team.adapter.in.web;

import com.everyTing.core.dto.Response;
import com.everyTing.core.resolver.LoginMember;
import com.everyTing.core.resolver.LoginMemberInfo;
import com.everyTing.team.adapter.in.web.docs.TeamMemberControllerDocs;
import com.everyTing.team.application.port.in.TeamMemberUseCase;
import com.everyTing.team.application.port.in.command.TeamMemberFindCommand;
import com.everyTing.team.application.port.in.command.TeamMemberSaveCommand;
import com.everyTing.team.domain.TeamMembers;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/teams/{teamId}/members")
public class TeamMemberController implements TeamMemberControllerDocs {

    private final TeamMemberUseCase teamMemberUseCase;

    public TeamMemberController(TeamMemberUseCase teamMemberUseCase) {
        this.teamMemberUseCase = teamMemberUseCase;
    }

    @GetMapping
    public Response<TeamMembers> memberList(@PathVariable Long teamId) {
        TeamMemberFindCommand command = TeamMemberFindCommand.of(teamId);
        return Response.success(teamMemberUseCase.findTeamMembers(command));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response<Long> memberSave(@PathVariable Long teamId,
        @LoginMember LoginMemberInfo loginMemberInfo) {
        teamMemberUseCase.saveTeamMember(TeamMemberSaveCommand.of(teamId, loginMemberInfo.getId()));
        return Response.success();
    }
}
