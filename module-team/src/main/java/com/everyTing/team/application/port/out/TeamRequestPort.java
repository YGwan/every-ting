package com.everyTing.team.application.port.out;

public interface TeamRequestPort {

    Long countTodayRequest(Long fromTeamId);

    Long saveTeamRequest(Long fromTeamId, Long toTeamId);
}
