package com.everyTing.team.adapter.in.web.docs;

import com.everyTing.core.dto.Response;
import com.everyTing.core.resolver.LoginMemberInfo;
import com.everyTing.team.application.port.in.command.TeamLikeFindCommand;
import com.everyTing.team.domain.TeamLikes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "팀 좋아요 API")
public interface TeamLikeControllerDocs {

    @Operation(summary = "좋아요 조회", description = "좋아요 한 fromTeam 멤버(teamMember)의 id들을 반환합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = @Content)})
    Response<TeamLikes> teamLikeList(Long fromTeamId, Long toTeamId);

    @Operation(summary = "좋아요")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "좋아요 성공", content = @Content),
        @ApiResponse(responseCode = "403", description = "TEAM_010, TEAM_011, TEAM_012, TEAM_014", content = @Content)})
    Response<Void> teamLikeSave(Long fromTeamId, Long toTeamId, LoginMemberInfo loginMemberInfo);

    @Operation(summary = "좋아요 취소")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "좋아요 취소 성공", content = @Content),
        @ApiResponse(responseCode = "403", description = "TEAM_010", content = @Content)})
    Response<Void> teamLikeRemove(Long fromTeamId, Long toTeamId, LoginMemberInfo loginMemberInfo);
}
