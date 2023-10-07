package com.everyTing.team.adapter.in.web;

import com.everyTing.core.dto.Response;
import com.everyTing.team.adapter.in.web.request.TeamSaveRequest;
import com.everyTing.team.application.port.in.TeamUseCase;
import com.everyTing.team.application.port.in.command.TeamSaveCommand;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/teams")
public class TeamController {

    private final Long mockMemberId = 100L;

    private final TeamUseCase teamUseCase;

    public TeamController(TeamUseCase teamUseCase) {
        this.teamUseCase = teamUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response teamSave(@RequestBody TeamSaveRequest request) {
        TeamSaveCommand command = TeamSaveCommand.of(mockMemberId, request.getName(),
            request.getMemberLimit(), request.getRegion(), request.getHashtags());

        return Response.success(teamUseCase.saveTeam(command));
    }
}
