package com.everyTing.team.adapter.in.web.docs;

import com.everyTing.core.dto.Response;
import com.everyTing.core.resolver.LoginMemberInfo;
import com.everyTing.core.swagger.ApiErrorCode;
import com.everyTing.team.adapter.in.web.request.TeamRequestSaveRequest;
import com.everyTing.team.common.exception.errorCode.details.NotTeamLeaderErrorCode;
import com.everyTing.team.common.exception.errorCode.details.TeamNotFoundErrorCode;
import com.everyTing.team.common.exception.errorCode.details.TeamRequestNotFoundErrorCode;
import com.everyTing.team.common.exception.errorCode.details.TeamRequestSaveErrorCode;
import com.everyTing.team.domain.TeamRequest;
import com.everyTing.team.domain.TeamRequests;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "미팅 요청 API")
public interface TeamRequestControllerDocs {

    @Operation(summary = "미팅 요청")
    @ApiErrorCode(values = {NotTeamLeaderErrorCode.class, TeamRequestSaveErrorCode.class, TeamNotFoundErrorCode.class})
    Response<Long> requestSave(TeamRequestSaveRequest request, LoginMemberInfo loginMemberInfo);

    @Operation(summary = "fromTeamId, toTeamId 를 이용한 미팅 요청 조회")
    Response<TeamRequests> requestList(
        @Parameter(description = "required x") @RequestParam(required = false) Long fromTeamId,
        @Parameter(description = "required x") @RequestParam(required = false) Long toTeamId);

    @Operation(summary = "requestId 를 이용한 미팅 요청 조회")
    @ApiErrorCode(values = TeamRequestNotFoundErrorCode.class)
    Response<TeamRequest> requestDetails(Long requestId);

    @Operation(summary = "미팅 요청 거절, 미팅 요청 취소")
    @ApiErrorCode(values = {NotTeamLeaderErrorCode.class, TeamRequestNotFoundErrorCode.class})
    Response<Void> requestRemove(Long requestId, LoginMemberInfo loginMemberInfo);
}
