package com.everyTing.team.adapter.in.web;

import com.everyTing.core.dto.Response;
import com.everyTing.core.resolver.LoginMember;
import com.everyTing.core.resolver.LoginMemberInfo;
import com.everyTing.team.adapter.in.web.docs.TeamHashtagControllerDocs;
import com.everyTing.team.adapter.in.web.request.TeamHashtagModifyRequest;
import com.everyTing.team.application.port.in.TeamHashtagUseCase;
import com.everyTing.team.application.port.in.command.TeamHashtagFindCommand;
import com.everyTing.team.application.port.in.command.TeamHashtagModifyCommand;
import com.everyTing.team.domain.TeamHashtags;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/teams/{teamId}/hashtags")
public class TeamHashtagController implements TeamHashtagControllerDocs {

    private final TeamHashtagUseCase hashtagUseCase;

    public TeamHashtagController(TeamHashtagUseCase hashtagUseCase) {
        this.hashtagUseCase = hashtagUseCase;
    }

    @GetMapping
    public Response<TeamHashtags> hashtagList(@PathVariable Long teamId) {
        final TeamHashtagFindCommand command = TeamHashtagFindCommand.of(teamId);
        return Response.success(hashtagUseCase.findHashtags(command));
    }

    @PutMapping
    public Response<Void> hashtagModify(@PathVariable Long teamId,
        @RequestBody TeamHashtagModifyRequest request,
        @LoginMember LoginMemberInfo loginMemberInfo) {
        final TeamHashtagModifyCommand command = TeamHashtagModifyCommand.of(teamId,
            request.getRemovedHashtagIds(), request.getNewHashtags(), loginMemberInfo.getId());
        hashtagUseCase.modifyHashtags(command);
        return Response.success();
    }
}
