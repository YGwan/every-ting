package com.everyTing.team.adapter.in.web;

import com.everyTing.core.dto.Response;
import com.everyTing.core.resolver.LoginMember;
import com.everyTing.core.resolver.LoginMemberInfo;
import com.everyTing.team.adapter.in.web.docs.MyTeamControllerDocs;
import com.everyTing.team.adapter.out.persistence.entity.data.Role;
import com.everyTing.team.application.port.in.MyTeamUseCase;
import com.everyTing.team.application.port.in.command.MyTeamFindCommand;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
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
}
