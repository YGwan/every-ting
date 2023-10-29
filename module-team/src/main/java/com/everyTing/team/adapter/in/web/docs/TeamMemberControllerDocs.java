package com.everyTing.team.adapter.in.web.docs;

import com.everyTing.core.dto.Response;
import com.everyTing.core.resolver.LoginMemberInfo;
import com.everyTing.core.swagger.ApiErrorCode;
import com.everyTing.team.common.exception.errorCode.details.NotTeamLeaderErrorCode;
import com.everyTing.team.common.exception.errorCode.details.TeamMemberRemoveErrorCode;
import com.everyTing.team.common.exception.errorCode.details.TeamMemberSaveErrorCode;
import com.everyTing.team.common.exception.errorCode.details.TeamNotFoundErrorCode;
import com.everyTing.team.domain.TeamMembers;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "팀원 관련 API")
public interface TeamMemberControllerDocs {

    @Operation(summary = "팀원 조회")
    @ApiErrorCode(values = TeamNotFoundErrorCode.class)
    Response<TeamMembers> memberList(Long teamId);

    @Operation(summary = "팀원 추가, 팀 가입")
    @ApiErrorCode(values = {TeamNotFoundErrorCode.class, TeamMemberSaveErrorCode.class})
    Response<Long> memberSave(Long teamId, LoginMemberInfo loginMemberInfo);

    @Operation(summary = "팀원 추방")
    @ApiErrorCode(values = {NotTeamLeaderErrorCode.class, TeamMemberRemoveErrorCode.class})
    Response<Void> memberRemove(Long teamId, Long teamMemberId, LoginMemberInfo loginMemberInfo);
}
