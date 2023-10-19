package com.everyTing.team.adapter.in.web;

import com.everyTing.core.dto.Response;
import com.everyTing.core.resolver.LoginMember;
import com.everyTing.core.resolver.LoginMemberInfo;
import com.everyTing.team.adapter.in.web.docs.TeamRequestControllerDocs;
import com.everyTing.team.application.port.in.TeamRequestUseCase;
import com.everyTing.team.application.port.in.command.TeamRequestSaveCommand;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/teams/{teamId}/requests")
public class TeamRequestController implements TeamRequestControllerDocs {

    private final TeamRequestUseCase teamRequestUseCase;

    public TeamRequestController(TeamRequestUseCase teamRequestUseCase) {
        this.teamRequestUseCase = teamRequestUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response<Long> requestSave(@PathVariable Long teamId,
        @RequestParam Long toTeamId, @LoginMember LoginMemberInfo loginMemberInfo) {
        final TeamRequestSaveCommand command = TeamRequestSaveCommand.of(teamId, toTeamId,
            loginMemberInfo.getId());
        return Response.success(teamRequestUseCase.saveTeamRequest(command));
    }
}
