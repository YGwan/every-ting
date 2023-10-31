package com.everyTing.team.adapter.in.web.docs;

import com.everyTing.core.dto.Response;
import com.everyTing.core.resolver.LoginMemberInfo;
import com.everyTing.team.adapter.in.web.request.TeamSaveRequest;
import com.everyTing.team.domain.Team;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "팀 API")
public interface TeamControllerDocs {

    @Operation(summary = "id로 팀 조회")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "조회 완료"),
        @ApiResponse(responseCode = "404", description = "TEAM_006", content = @Content)})
    Response<Team> teamDetails(Long teamId);

    @Operation(summary = "code로 팀 조회")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "조회 완료"),
        @ApiResponse(responseCode = "404", description = "TEAM_006", content = @Content)})
    Response<Team> teamDetails(String teamCode);

    @Operation(summary = "팀 생성")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "생성 완료, 생성된 팀의 id 반환"),
        @ApiResponse(responseCode = "400", description = "TEAM_005", content = @Content),
        @ApiResponse(responseCode = "400", description = "유효성 검사 실패", content = @Content)})
    Response<Long> teamSave(TeamSaveRequest request, LoginMemberInfo loginMemberInfo);

    @Operation(summary = "팀 삭제")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "삭제 완료", content = @Content),
        @ApiResponse(responseCode = "400", description = "TEAM_015, TEAM_027", content = @Content)
    })
    Response<Void> teamRemove(Long teamId, LoginMemberInfo loginMemberInfo);
}
