package com.everyTing.team.application.service;

import com.everyTing.team.application.port.in.TeamHashtagUseCase;
import com.everyTing.team.application.port.in.command.TeamHashtagFindCommand;
import com.everyTing.team.application.port.out.TeamHashtagPort;
import com.everyTing.team.domain.TeamHashtags;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class TeamHashtagService implements TeamHashtagUseCase {

    private final TeamHashtagPort hashtagPort;

    public TeamHashtagService(TeamHashtagPort hashtagPort) {
        this.hashtagPort = hashtagPort;
    }

    @Override
    public TeamHashtags findHashtags(TeamHashtagFindCommand command) {
        return hashtagPort.findHashtags(command.getTeamId());
    }
}
