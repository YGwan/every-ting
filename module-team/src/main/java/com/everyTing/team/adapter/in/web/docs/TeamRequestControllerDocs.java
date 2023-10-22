package com.everyTing.team.adapter.in.web.docs;

import com.everyTing.core.dto.Response;
import com.everyTing.core.resolver.LoginMemberInfo;
import com.everyTing.team.adapter.in.web.request.TeamRequestSaveRequest;
import com.everyTing.team.domain.TeamRequests;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "과팅 요청 API")
public interface TeamRequestControllerDocs {

    @Operation(summary = "과팅 요청")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "요청 완료"),
        @ApiResponse(responseCode = "400", description = "TEAM_011, TEAM_014, TEAM_016, TEAM_017, TEAM_018, TEAM_006"),
        @ApiResponse(responseCode = "403", description = "TEAM_015")})
    Response<Long> requestSave(TeamRequestSaveRequest request, LoginMemberInfo loginMemberInfo);

    @Operation(summary = "과팅 요청 조회")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "조회 성공")})
    Response<TeamRequests> requestDetails(
        @Parameter(description = "required x") @RequestParam(required = false) Long fromTeamId,
        @Parameter(description = "required x") @RequestParam(required = false) Long toTeamId);
}
