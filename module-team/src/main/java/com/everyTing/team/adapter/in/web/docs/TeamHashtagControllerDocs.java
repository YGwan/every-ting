package com.everyTing.team.adapter.in.web.docs;

import com.everyTing.core.dto.Response;
import com.everyTing.team.domain.TeamHashtags;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "팀 해시태그 API")
public interface TeamHashtagControllerDocs {

    @Operation(summary = "팀 해시태그 조회")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "조회 완료"),
        @ApiResponse(responseCode = "404", description = "TEAM_006", content = @Content)})
    Response<TeamHashtags> hashtagList(Long teamId);
}
