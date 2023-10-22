package com.everyTing.team.adapter.in.web;

import com.everyTing.core.dto.Response;
import com.everyTing.core.resolver.LoginMember;
import com.everyTing.core.resolver.LoginMemberInfo;
import com.everyTing.team.adapter.in.web.docs.TeamRequestControllerDocs;
import com.everyTing.team.adapter.in.web.request.TeamRequestSaveRequest;
import com.everyTing.team.application.port.in.TeamRequestUseCase;
import com.everyTing.team.application.port.in.command.TeamRequestFindCommand;
import com.everyTing.team.application.port.in.command.TeamRequestSaveCommand;
import com.everyTing.team.domain.TeamRequests;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/teams/requests")
public class TeamRequestController implements TeamRequestControllerDocs {

    private final TeamRequestUseCase teamRequestUseCase;

    public TeamRequestController(TeamRequestUseCase teamRequestUseCase) {
        this.teamRequestUseCase = teamRequestUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response<Long> requestSave(@RequestBody TeamRequestSaveRequest request, @LoginMember LoginMemberInfo loginMemberInfo) {
        final TeamRequestSaveCommand command = TeamRequestSaveCommand.of(
            request.getFromTeamId(), request.getToTeamId(), loginMemberInfo.getId());
        return Response.success(teamRequestUseCase.saveTeamRequest(command));
    }

    @GetMapping
    public Response<TeamRequests> requestDetails(
        @PathVariable(required = false) Long fromTeamId,
        @PathVariable(required = false) Long toTeamId) {
        final TeamRequestFindCommand command = TeamRequestFindCommand.of(fromTeamId, toTeamId);
        return Response.success(teamRequestUseCase.findTeamRequest(command));
    }
}
