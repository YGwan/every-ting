package com.everyTing.team.application.service;

import com.everyTing.team.application.port.in.OtherTeamUseCase;
import com.everyTing.team.application.port.in.command.OtherTeamFindListCommand;
import com.everyTing.team.application.port.out.OtherTeamPort;
import com.everyTing.team.domain.TeamWithLikeCount;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class OtherTeamService implements OtherTeamUseCase {

    private final OtherTeamPort otherTeamPort;

    public OtherTeamService(OtherTeamPort otherTeamPort) {
        this.otherTeamPort = otherTeamPort;
    }

    @Override
    public Slice<TeamWithLikeCount> findTeamList(OtherTeamFindListCommand command) {
        return otherTeamPort.findOtherTeams(command.getMyTeamId(), command.getPageable());
    }
}
