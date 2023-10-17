package com.everyTing.team.adapter.in.web;

import com.everyTing.core.dto.Response;
import com.everyTing.core.resolver.LoginMember;
import com.everyTing.core.resolver.LoginMemberInfo;
import com.everyTing.team.adapter.in.web.docs.TeamControllerDocs;
import com.everyTing.team.adapter.in.web.request.TeamSaveRequest;
import com.everyTing.team.application.port.in.TeamUseCase;
import com.everyTing.team.application.port.in.command.TeamFindByCodeCommand;
import com.everyTing.team.application.port.in.command.TeamFindByIdCommand;
import com.everyTing.team.application.port.in.command.TeamSaveCommand;
import com.everyTing.team.domain.Team;
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
@RequestMapping("/api/v1/teams")
public class TeamController implements TeamControllerDocs {

    private final TeamUseCase teamUseCase;

    public TeamController(TeamUseCase teamUseCase) {
        this.teamUseCase = teamUseCase;
    }

    @GetMapping("/{teamId}")
    public Response<Team> teamDetails(@PathVariable Long teamId) {
        return Response.success(teamUseCase.findTeamById(TeamFindByIdCommand.of(teamId)));
    }

    @GetMapping("/by-teamcode")
    public Response<Team> teamDetails(@RequestParam String teamCode) {
        return Response.success(teamUseCase.findTeamByCode(TeamFindByCodeCommand.of(teamCode)));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response<Long> teamSave(@RequestBody TeamSaveRequest request,
        @LoginMember LoginMemberInfo loginMemberInfo) {
        TeamSaveCommand command = TeamSaveCommand.of(loginMemberInfo.getId(), request.getName(),
            request.getMemberLimit(), request.getRegions(), request.getHashtags());

        return Response.success(teamUseCase.saveTeam(command));
    }
}
