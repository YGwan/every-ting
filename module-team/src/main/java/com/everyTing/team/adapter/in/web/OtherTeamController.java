package com.everyTing.team.adapter.in.web;

import com.everyTing.core.dto.Response;
import com.everyTing.team.adapter.in.web.docs.OtherTeamControllerDocs;
import com.everyTing.team.application.port.in.OtherTeamUseCase;
import com.everyTing.team.application.port.in.command.OtherTeamFindListCommand;
import com.everyTing.team.domain.Team;
import com.everyTing.team.domain.TeamWithLikeCount;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/teams/{teamId}")
public class OtherTeamController implements OtherTeamControllerDocs {

    private final OtherTeamUseCase otherTeamUseCase;

    public OtherTeamController(OtherTeamUseCase otherTeamUseCase) {
        this.otherTeamUseCase = otherTeamUseCase;
    }

    @GetMapping("/other-gender-teams")
    public Response<Slice<TeamWithLikeCount>> teamList(@PathVariable Long teamId, Pageable pageable) {
        OtherTeamFindListCommand command = OtherTeamFindListCommand.of(teamId, pageable);
        return Response.success(otherTeamUseCase.findTeamList(command));
    }
}
