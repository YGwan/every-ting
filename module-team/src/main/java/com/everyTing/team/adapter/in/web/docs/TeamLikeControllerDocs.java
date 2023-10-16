package com.everyTing.team.adapter.in.web.docs;

import com.everyTing.core.dto.Response;
import com.everyTing.core.resolver.LoginMemberInfo;
import com.everyTing.team.adapter.in.web.request.TeamLikeRequest;
import com.everyTing.team.adapter.in.web.request.TeamUnlikeRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "팀 좋아요 API")
public interface TeamLikeControllerDocs {

    @Operation(summary = "상대팀 좋아요")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "좋아요 성공", content = @Content),
        @ApiResponse(responseCode = "403", description = "TEAM_010, TEAM_011, TEAM_012, TEAM_014", content = @Content)})
    Response<Void> teamLikeSave(Long teamId, TeamLikeRequest request, LoginMemberInfo loginMemberInfo);

    @Operation(summary = "상대팀 좋아요 취소")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "좋아요 취소 성공", content = @Content),
        @ApiResponse(responseCode = "403", description = "TEAM_010", content = @Content)})
    Response<Void> teamLikeRemove(Long teamId, TeamUnlikeRequest request, LoginMemberInfo loginMemberInfo);
}
