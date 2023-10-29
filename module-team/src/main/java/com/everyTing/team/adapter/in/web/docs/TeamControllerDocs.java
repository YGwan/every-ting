package com.everyTing.team.adapter.in.web.docs;

import com.everyTing.core.dto.Response;
import com.everyTing.core.resolver.LoginMemberInfo;
import com.everyTing.core.swagger.ApiErrorCode;
import com.everyTing.team.adapter.in.web.request.TeamSaveRequest;
import com.everyTing.team.common.exception.errorCode.details.TeamCreateConstraintErrorCode;
import com.everyTing.team.common.exception.errorCode.details.TeamNotFoundErrorCode;
import com.everyTing.team.common.exception.errorCode.details.TeamRemoveErrorCode;
import com.everyTing.team.domain.Team;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "팀 API")
public interface TeamControllerDocs {

    @Operation(summary = "id로 팀 조회")
    @ApiErrorCode(values = TeamNotFoundErrorCode.class)
    Response<Team> teamDetails(Long teamId);

    @Operation(summary = "code로 팀 조회")
    @ApiErrorCode(values = TeamNotFoundErrorCode.class)
    Response<Team> teamDetails(String teamCode);

    @Operation(summary = "남은 요청 횟수 조회")
    Response<Long> teamRequestCount(Long teamId);

    @Operation(summary = "남은 매칭 횟수 조회")
    Response<Long> teamDateCount(Long teamId);

    @Operation(summary = "팀 생성")
    @ApiErrorCode(values = TeamCreateConstraintErrorCode.class)
    Response<Long> teamSave(TeamSaveRequest request, LoginMemberInfo loginMemberInfo);

    @Operation(summary = "팀 삭제")
    @ApiErrorCode(values = TeamRemoveErrorCode.class)
    Response<Void> teamRemove(Long teamId, LoginMemberInfo loginMemberInfo);
}
