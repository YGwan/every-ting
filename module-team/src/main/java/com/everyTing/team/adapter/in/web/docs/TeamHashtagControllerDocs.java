package com.everyTing.team.adapter.in.web.docs;

import com.everyTing.core.dto.Response;
import com.everyTing.core.resolver.LoginMemberInfo;
import com.everyTing.core.swagger.ApiErrorCode;
import com.everyTing.team.adapter.in.web.request.TeamHashtagModifyRequest;
import com.everyTing.team.common.exception.errorCode.details.NotTeamLeaderErrorCode;
import com.everyTing.team.common.exception.errorCode.details.TeamNotFoundErrorCode;
import com.everyTing.team.domain.TeamHashtags;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "팀 해시태그 API")
public interface TeamHashtagControllerDocs {

    @Operation(summary = "팀 해시태그 조회")
    @ApiErrorCode(values = TeamNotFoundErrorCode.class)
    Response<TeamHashtags> hashtagList(Long teamId);

    @Operation(summary = "팀 해시태그 수정")
    @ApiErrorCode(values = NotTeamLeaderErrorCode.class)
    Response<Void> hashtagModify(Long teamId, TeamHashtagModifyRequest request,
        LoginMemberInfo loginMemberInfo);
}
