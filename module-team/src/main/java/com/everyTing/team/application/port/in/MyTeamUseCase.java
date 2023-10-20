package com.everyTing.team.application.port.in;

import com.everyTing.team.application.port.in.command.MyTeamFindCommand;
import java.util.List;

public interface MyTeamUseCase {

    List<Long> findMyTeams(MyTeamFindCommand command);
}
