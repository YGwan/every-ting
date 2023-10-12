package com.everyTing.team.adapter.in.web.docs;

import com.everyTing.core.dto.Response;
import com.everyTing.team.adapter.in.web.request.TeamSaveRequest;
import com.everyTing.team.domain.Team;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@Tag(name = "팀 API")
@RequestMapping("/api/v1/teams")
public interface TeamControllerDocs {

    @Operation(summary = "id로 팀 조회")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "조회 완료"),
        @ApiResponse(responseCode = "404", description = "TEAM_006", content = @Content)})
    @GetMapping("/{teamId}")
    Response<Team> teamDetails(@PathVariable Long teamId);

    @Operation(summary = "code로 팀 조회")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "조회 완료"),
        @ApiResponse(responseCode = "404", description = "TEAM_006", content = @Content)})
    @GetMapping("/by-teamcode")
    Response<Team> teamDetails(@RequestParam String teamCode);

    @Operation(summary = "팀 생성")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "생성 완료, 생성된 팀의 id 반환"),
        @ApiResponse(responseCode = "400", description = "TEAM_005", content = @Content),
        @ApiResponse(responseCode = "400", description = "유효성 검사 실패", content = @Content)})
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Response<Long> teamSave(@RequestBody TeamSaveRequest request);
}
