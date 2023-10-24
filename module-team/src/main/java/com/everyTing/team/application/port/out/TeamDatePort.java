package com.everyTing.team.application.port.out;

import java.time.LocalDateTime;

public interface TeamDatePort {

    Long countByTeamIdAndCreatedAtAfter(Long teamId, LocalDateTime time);

    Long saveTeamDate(Long womenTeamId, Long menTeamId);

    Boolean existsTeamDate(Long womenTeamId, Long menTeamId);
}
