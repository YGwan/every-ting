package com.everyTing.team.application.port.out;

import com.everyTing.team.domain.TeamWithLikeCount;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface OtherTeamPort {

    Slice<TeamWithLikeCount> findOtherTeams(Long myTeamId, Pageable pageable);
}
