package com.everyTing.team.adapter.in.web;

import com.everyTing.core.dto.Response;
import com.everyTing.team.adapter.in.web.docs.TeamHashtagControllerDocs;
import com.everyTing.team.application.port.in.TeamHashtagUseCase;
import com.everyTing.team.application.port.in.command.TeamHashtagFindCommand;
import com.everyTing.team.domain.TeamHashtags;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
        TeamHashtagFindCommand command = TeamHashtagFindCommand.of(teamId);

        return Response.success(hashtagUseCase.findHashtags(command));
    }
}
