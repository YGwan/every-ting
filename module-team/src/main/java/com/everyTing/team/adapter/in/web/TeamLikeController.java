package com.everyTing.team.adapter.in.web;

import com.everyTing.core.dto.Response;
import com.everyTing.core.resolver.LoginMember;
import com.everyTing.core.resolver.LoginMemberInfo;
import com.everyTing.team.adapter.in.web.docs.TeamLikeControllerDocs;
import com.everyTing.team.adapter.in.web.request.TeamLikeRequest;
import com.everyTing.team.adapter.in.web.request.TeamUnlikeRequest;
import com.everyTing.team.application.port.in.TeamLikeUseCase;
import com.everyTing.team.application.port.in.command.TeamLikeRemoveCommand;
import com.everyTing.team.application.port.in.command.TeamLikeSaveCommand;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/teams/{teamId}/likes")
public class TeamLikeController implements TeamLikeControllerDocs {

    private final TeamLikeUseCase teamLikeUseCase;

    public TeamLikeController(TeamLikeUseCase teamLikeUseCase) {
        this.teamLikeUseCase = teamLikeUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response<Void> teamLikeSave(@PathVariable Long teamId,
        @RequestBody TeamLikeRequest request, @LoginMember LoginMemberInfo loginMemberInfo) {
        teamLikeUseCase.saveTeamLike(
            TeamLikeSaveCommand.of(request.getLikedTeamId(), teamId, loginMemberInfo.getId()));
        return Response.success();
    }

    @DeleteMapping
    public Response<Void> teamLikeRemove(@PathVariable Long teamId,
        @RequestBody TeamUnlikeRequest request, @LoginMember LoginMemberInfo loginMemberInfo) {
        teamLikeUseCase.removeTeamLike(
            TeamLikeRemoveCommand.of(request.getLikedTeamId(), teamId, loginMemberInfo.getId()));
        return Response.success();
    }
}
