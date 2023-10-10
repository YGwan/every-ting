package com.everyTing.team.adapter.in.web.docs;

import com.everyTing.core.dto.Response;
import com.everyTing.team.adapter.in.web.request.TeamSaveRequest;
import com.everyTing.team.domain.Team;
import com.everyTing.team.domain.TeamHashtags;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Tag(name = "팀 해시태그 API")
@RequestMapping("/api/v1/teams/{teamId}/hashtags")
public interface TeamHashtagControllerDocs {

    @Operation(summary = "팀 해시태그 조회")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "조회 완료", useReturnTypeSchema = true),
        @ApiResponse(responseCode = "404", description = "TEAM_006", content = @Content)})
    @GetMapping
    Response<TeamHashtags> hashtagList(@PathVariable Long teamId);
}
