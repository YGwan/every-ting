package com.everyTing.team.application.service;

import com.everyTing.team.application.port.in.TeamLikeUseCase;
import com.everyTing.team.application.port.in.command.TeamLikeFindCommand;
import com.everyTing.team.application.port.in.command.TeamLikeRemoveCommand;
import com.everyTing.team.application.port.in.command.TeamLikeSaveCommand;
import com.everyTing.team.application.port.out.TeamLikePort;
import com.everyTing.team.domain.TeamLikes;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class TeamLikeService implements TeamLikeUseCase {

    private final TeamLikePort teamLikePort;

    public TeamLikeService(TeamLikePort teamLikePort) {
        this.teamLikePort = teamLikePort;
    }

    @Override
    public TeamLikes findTeamLike(TeamLikeFindCommand command) {
        return teamLikePort.findTeamLikeByFromTeamIdAndToTeamId(command.getFromTeamId(),
            command.getToTeamId());
    }

    @Override
    @Transactional
    public void saveTeamLike(TeamLikeSaveCommand command) {
        teamLikePort.saveTeamLike(command.getToTeamId(), command.getFromTeamId(),
            command.getFromMemberId());
    }

    @Override
    @Transactional
    public void removeTeamLike(TeamLikeRemoveCommand command) {
        teamLikePort.removeTeamLike(command.getToTeamId(), command.getFromTeamId(),
            command.getFromMemberId());
    }
}
