package com.everyTing.team.application.port.in;

import com.everyTing.team.application.port.in.command.TeamHashtagFindCommand;
import com.everyTing.team.domain.TeamHashtags;

public interface TeamHashtagUseCase {

    TeamHashtags findHashtags(TeamHashtagFindCommand command);
}