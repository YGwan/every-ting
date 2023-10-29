package com.everyTing.team.adapter.in.web;

import com.everyTing.core.dto.Response;
import com.everyTing.core.resolver.LoginMember;
import com.everyTing.core.resolver.LoginMemberInfo;
import com.everyTing.team.adapter.in.web.docs.TeamDateControllerDocs;
import com.everyTing.team.adapter.in.web.request.TeamDateSaveRequest;
import com.everyTing.team.application.port.in.TeamDateUseCase;
import com.everyTing.team.application.port.in.command.TeamDateSaveCommand;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/teams/dates")
public class TeamDateController implements TeamDateControllerDocs {

    private final TeamDateUseCase teamDateUseCase;

    public TeamDateController(TeamDateUseCase teamDateUseCase) {
        this.teamDateUseCase = teamDateUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response<Long> teamDateSave(@RequestBody TeamDateSaveRequest request,
        @LoginMember LoginMemberInfo loginMemberInfo) {
        final TeamDateSaveCommand command = TeamDateSaveCommand.of(request.getRequestId(),
            loginMemberInfo.getId());
        return Response.success(teamDateUseCase.saveTeamDate(command));
    }
}
