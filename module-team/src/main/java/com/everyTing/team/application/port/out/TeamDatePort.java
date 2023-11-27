package com.everyTing.team.application.port.out;

import com.everyTing.team.domain.TeamDateWithGender;
import com.everyTing.team.domain.TeamDates;
import java.time.LocalDateTime;
import java.util.List;

public interface TeamDatePort {

    Long countByTeamIdAndCreatedAtAfter(Long teamId, LocalDateTime time);

    Long saveTeamDate(Long womenTeamId, Long menTeamId);

    Boolean existsTeamDate(Long womenTeamId, Long menTeamId);

    TeamDateWithGender findTeamDate(Long dateId);

    TeamDates findTeamDatesByTeamIdIn(List<Long> teamIds);
}
