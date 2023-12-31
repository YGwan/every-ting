package com.everyTing.team.application.port.in;

import com.everyTing.team.application.port.in.command.MyTeamDateFindCommand;
import com.everyTing.team.application.port.in.command.MyTeamExistsCommand;
import com.everyTing.team.application.port.in.command.MyTeamFindCommand;
import com.everyTing.team.application.port.in.command.MyTeamRemoveCommand;
import com.everyTing.team.application.port.in.command.MyTeamRequestFindCommand;
import com.everyTing.team.domain.TeamDates;
import com.everyTing.team.domain.TeamRequests;
import java.util.List;

public interface MyTeamUseCase {

    Boolean existsMyTeam(MyTeamExistsCommand command);

    List<Long> findMyTeams(MyTeamFindCommand command);

    TeamDates findMyTeamDates(MyTeamDateFindCommand command);

    TeamRequests findMyTeamRequests(MyTeamRequestFindCommand command);

    void removeMyTeam(MyTeamRemoveCommand command);
}
