package com.everyTing.team.adapter.in.web.docs;

import com.everyTing.core.dto.Response;
import com.everyTing.core.resolver.LoginMember;
import com.everyTing.core.resolver.LoginMemberInfo;
import com.everyTing.team.domain.TeamMembers;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Tag(name = "팀원 관련 API")
@RequestMapping("/api/v1/teams/{teamId}/members")
public interface TeamMemberControllerDocs {

    @Operation(summary = "팀원 조회")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "조회 완료"),
        @ApiResponse(responseCode = "404", description = "TEAM_006", content = @Content)})
    @GetMapping
    Response<TeamMembers> memberList(@PathVariable Long teamId);

    @Operation(summary = "팀원 추가, 팀 가입")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "팀원 추가 및 가입 완료", content = @Content),
        @ApiResponse(responseCode = "404", description = "TEAM_006, TEAM_007, TEAM_008, TEAM_009", content = @Content)})
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Response<Void> memberSave(@PathVariable Long teamId,
        @LoginMember LoginMemberInfo loginMemberInfo);
}
