package com.everyTing.team.adapter.in.web.docs;

import com.everyTing.core.dto.Response;
import com.everyTing.core.resolver.LoginMemberInfo;
import com.everyTing.team.adapter.out.persistence.entity.data.Role;
import com.everyTing.team.domain.TeamDates;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;

@Tag(name = "내 팀 API")
public interface MyTeamControllerDocs {

    @Operation(summary = "내 팀 조회")
    Response<List<Long>> myTeamList(Role role, LoginMemberInfo memberInfo);

    @Operation(summary = "내가 속한 팀 미팅 조회")
    Response<TeamDates> myTeamDateList(LoginMemberInfo loginMemberInfo);
}
