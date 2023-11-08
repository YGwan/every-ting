package com.everyTing.team.adapter.in.web.docs;

import com.everyTing.core.dto.Response;
import com.everyTing.core.resolver.LoginMember;
import com.everyTing.core.resolver.LoginMemberInfo;
import com.everyTing.team.adapter.out.persistence.entity.data.Role;
import com.everyTing.team.domain.TeamDates;
import com.everyTing.team.domain.TeamRequest;
import com.everyTing.team.domain.TeamRequests;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name = "내 팀 API")
public interface MyTeamControllerDocs {

    @Operation(summary = "내 팀 조회")
    Response<List<Long>> myTeamList(Role role, LoginMemberInfo memberInfo);

    @Operation(summary = "내가 속한 팀 미팅 매칭 현황 조회")
    Response<TeamDates> myTeamDateList(LoginMemberInfo loginMemberInfo);

    @Operation(summary = "내가 속한 팀 미팅 요청 현황 조회")
    Response<TeamRequests> myTeamRequestList(LoginMemberInfo loginMemberInfo);

    @Operation(summary = "속한 팀에서 나가기")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "403", description = "TEAM_010", content = @Content),
        @ApiResponse(responseCode = "404", description = "TEAM_026", content = @Content)
    })
    Response<Void> myTeamRemove(Long teamId, LoginMemberInfo loginMemberInfo);
}
