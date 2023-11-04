package com.everyTing.team.application.port.in;

import com.everyTing.team.application.port.in.command.MyTeamDateFindCommand;
import com.everyTing.team.application.port.in.command.MyTeamFindCommand;
import com.everyTing.team.domain.TeamDates;
import java.util.List;

public interface MyTeamUseCase {

    List<Long> findMyTeams(MyTeamFindCommand command);

    TeamDates findMyTeamDates(MyTeamDateFindCommand command);
}
