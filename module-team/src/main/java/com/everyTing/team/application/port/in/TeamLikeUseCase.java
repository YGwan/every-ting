package com.everyTing.team.application.port.in;

import com.everyTing.team.application.port.in.command.TeamLikeFindCommand;
import com.everyTing.team.application.port.in.command.TeamLikeRemoveCommand;
import com.everyTing.team.application.port.in.command.TeamLikeSaveCommand;
import com.everyTing.team.domain.TeamLikes;

public interface TeamLikeUseCase {

    TeamLikes findTeamLike(TeamLikeFindCommand command);

    void saveTeamLike(TeamLikeSaveCommand command);

    void removeTeamLike(TeamLikeRemoveCommand command);
}