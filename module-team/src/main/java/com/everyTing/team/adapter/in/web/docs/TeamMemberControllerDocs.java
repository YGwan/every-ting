package com.everyTing.team.adapter.in.web.docs;

import com.everyTing.core.dto.Response;
import com.everyTing.team.domain.TeamMembers;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "팀 멤버 API")
@RequestMapping("/api/v1/teams/{teamId}/members")
public interface TeamMemberControllerDocs {

    @Operation(summary = "팀 멤버 조회")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "조회 완료"),
        @ApiResponse(responseCode = "404", description = "TEAM_006", content = @Content)})
    @GetMapping
    Response<TeamMembers> memberList(@PathVariable Long teamId);
}
