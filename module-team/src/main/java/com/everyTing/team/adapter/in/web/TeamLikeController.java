package com.everyTing.team.adapter.in.web;

import com.everyTing.core.dto.Response;
import com.everyTing.core.resolver.LoginMember;
import com.everyTing.core.resolver.LoginMemberInfo;
import com.everyTing.team.adapter.in.web.docs.TeamLikeControllerDocs;
import com.everyTing.team.application.port.in.TeamLikeUseCase;
import com.everyTing.team.application.port.in.command.TeamLikeFindCommand;
import com.everyTing.team.application.port.in.command.TeamLikeRemoveCommand;
import com.everyTing.team.application.port.in.command.TeamLikeSaveCommand;
import com.everyTing.team.domain.TeamLikes;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/teams/likes")
public class TeamLikeController implements TeamLikeControllerDocs {

    private final TeamLikeUseCase teamLikeUseCase;

    public TeamLikeController(TeamLikeUseCase teamLikeUseCase) {
        this.teamLikeUseCase = teamLikeUseCase;
    }

    @GetMapping
    public Response<TeamLikes> teamLikeList(@RequestParam Long fromTeamId,
        @RequestParam Long toTeamId) {
        final TeamLikeFindCommand command = TeamLikeFindCommand.of(fromTeamId, toTeamId);
        return Response.success(teamLikeUseCase.findTeamLike(command));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response<Void> teamLikeSave(@RequestParam Long fromTeamId,
        @RequestParam Long toTeamId, @LoginMember LoginMemberInfo loginMemberInfo) {
        teamLikeUseCase.saveTeamLike(
            TeamLikeSaveCommand.of(toTeamId, fromTeamId, loginMemberInfo.getId()));
        return Response.success();
    }

    @DeleteMapping
    public Response<Void> teamLikeRemove(@RequestParam Long fromTeamId,
        @RequestParam Long toTeamId, @LoginMember LoginMemberInfo loginMemberInfo) {
        teamLikeUseCase.removeTeamLike(
            TeamLikeRemoveCommand.of(toTeamId, fromTeamId, loginMemberInfo.getId()));
        return Response.success();
    }
}
