package com.everyTing.team.application.port.in;

import com.everyTing.team.application.port.in.command.TeamLikeRemoveCommand;
import com.everyTing.team.application.port.in.command.TeamLikeSaveCommand;

public interface TeamLikeUseCase {

    void saveTeamLike(TeamLikeSaveCommand command);

    void removeTeamLike(TeamLikeRemoveCommand command);
}