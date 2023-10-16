package com.everyTing.team.application.port.in;

import com.everyTing.team.application.port.in.command.OtherTeamFindListCommand;
import com.everyTing.team.domain.TeamWithLikeCount;
import org.springframework.data.domain.Slice;

public interface OtherTeamUseCase {

    Slice<TeamWithLikeCount> findTeamList(OtherTeamFindListCommand command);
}
