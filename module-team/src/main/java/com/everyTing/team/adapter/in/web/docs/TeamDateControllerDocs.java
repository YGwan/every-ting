package com.everyTing.team.adapter.in.web.docs;

import com.everyTing.core.dto.Response;
import com.everyTing.core.resolver.LoginMemberInfo;
import com.everyTing.team.adapter.in.web.request.TeamDateSaveRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "매칭 API")
public interface TeamDateControllerDocs {

    @Operation(summary = "매칭 성사")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "매칭 성공", content = @Content),
        @ApiResponse(responseCode = "404", description = "TEAM_019",content = @Content),
        @ApiResponse(responseCode = "400", description = "TEAM_015, TEAM_020, TEAM_021, TEAM_022, TEAM_023, TEAM_024", content = @Content)})
    Response<Long> teamDateSave(TeamDateSaveRequest request, LoginMemberInfo loginMemberInfo);
}
