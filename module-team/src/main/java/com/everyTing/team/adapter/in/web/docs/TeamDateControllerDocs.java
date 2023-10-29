package com.everyTing.team.adapter.in.web.docs;

import com.everyTing.core.dto.Response;
import com.everyTing.core.resolver.LoginMemberInfo;
import com.everyTing.core.swagger.ApiErrorCode;
import com.everyTing.team.adapter.in.web.request.TeamDateSaveRequest;
import com.everyTing.team.common.exception.errorCode.details.TeamDateSaveErrorCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "매칭 API")
public interface TeamDateControllerDocs {

    @Operation(summary = "매칭 성사")
    @ApiErrorCode(values = TeamDateSaveErrorCode.class)
    Response<Long> teamDateSave(TeamDateSaveRequest request, LoginMemberInfo loginMemberInfo);
}
