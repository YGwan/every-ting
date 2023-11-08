package com.everyTing.team.adapter.in.web;

import com.everyTing.core.dto.Response;
import com.everyTing.core.resolver.LoginMember;
import com.everyTing.core.resolver.LoginMemberInfo;
import com.everyTing.team.adapter.in.web.docs.MyTeamControllerDocs;
import com.everyTing.team.adapter.out.persistence.entity.data.Role;
import com.everyTing.team.application.port.in.MyTeamUseCase;
import com.everyTing.team.application.port.in.command.MyTeamDateFindCommand;
import com.everyTing.team.application.port.in.command.MyTeamFindCommand;
import com.everyTing.team.application.port.in.command.MyTeamRemoveCommand;
import com.everyTing.team.application.port.in.command.MyTeamRequestFindCommand;
import com.everyTing.team.domain.TeamDates;
import com.everyTing.team.domain.TeamRequests;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/my/teams")
public class MyTeamController implements MyTeamControllerDocs {

    private final MyTeamUseCase myTeamUseCase;

    public MyTeamController(MyTeamUseCase myTeamUseCase) {
        this.myTeamUseCase = myTeamUseCase;
    }

    @GetMapping
    public Response<List<Long>> myTeamList(@RequestParam Role role,
        @LoginMember LoginMemberInfo memberInfo) {
        final MyTeamFindCommand command = MyTeamFindCommand.of(memberInfo.getId(), role);
        return Response.success(myTeamUseCase.findMyTeams(command));
    }

    @GetMapping("/dates")
    public Response<TeamDates> myTeamDateList(@LoginMember LoginMemberInfo loginMemberInfo) {
        final MyTeamDateFindCommand command = MyTeamDateFindCommand.of(loginMemberInfo.getId());
        return Response.success(myTeamUseCase.findMyTeamDates(command));
    }

    @GetMapping("/requests")
    public Response<TeamRequests> myTeamRequestList(@LoginMember LoginMemberInfo loginMemberInfo) {
        final MyTeamRequestFindCommand command = MyTeamRequestFindCommand.of(
            loginMemberInfo.getId());
        return Response.success(myTeamUseCase.findMyTeamRequests(command));
    }

    @DeleteMapping("/{teamId}")
    public Response<Void> myTeamRemove(@PathVariable Long teamId,
        @LoginMember LoginMemberInfo loginMemberInfo) {
        final MyTeamRemoveCommand command = MyTeamRemoveCommand.of(teamId, loginMemberInfo.getId());
        myTeamUseCase.removeMyTeam(command);
        return Response.success();
    }
}
