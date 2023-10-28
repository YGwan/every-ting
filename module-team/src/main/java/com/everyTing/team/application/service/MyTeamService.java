package com.everyTing.team.application.service;

import com.everyTing.team.application.port.in.MyTeamUseCase;
import com.everyTing.team.application.port.in.command.MyTeamDateFindCommand;
import com.everyTing.team.application.port.in.command.MyTeamFindCommand;
import com.everyTing.team.application.port.in.command.MyTeamRequestFindCommand;
import com.everyTing.team.application.port.out.TeamDatePort;
import com.everyTing.team.application.port.out.TeamMemberPort;
import com.everyTing.team.application.port.out.TeamRequestPort;
import com.everyTing.team.domain.TeamDates;
import com.everyTing.team.domain.TeamMember;
import com.everyTing.team.domain.TeamMembers;
import com.everyTing.team.domain.TeamRequests;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class MyTeamService implements MyTeamUseCase {

    private final TeamMemberPort teamMemberPort;
    private final TeamDatePort teamDatePort;
    private final TeamRequestPort teamRequestPort;

    public MyTeamService(TeamMemberPort teamMemberPort, TeamDatePort teamDatePort,
        TeamRequestPort teamRequestPort) {
        this.teamMemberPort = teamMemberPort;
        this.teamDatePort = teamDatePort;
        this.teamRequestPort = teamRequestPort;
    }

    @Override
    public List<Long> findMyTeams(MyTeamFindCommand command) {
        final TeamMembers myTeamMemberRecords = teamMemberPort.findTeamMembersByMemberIdAndRole(
            command.getMemberId(), command.getMyRole());

        return myTeamMemberRecords.getTeamMembers().stream()
                                  .map(TeamMember::getTeamId)
                                  .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public TeamDates findMyTeamDates(MyTeamDateFindCommand command) {
        List<Long> myTeamIds = getMyTeamIds(command.getMemberId());

        return teamDatePort.findTeamDatesByTeamIdIn(myTeamIds);
    }

    @Override
    public TeamRequests findMyTeamRequests(MyTeamRequestFindCommand command) {
        List<Long> myTeamIds = getMyTeamIds(command.getMemberId());

        return teamRequestPort.findTeamRequestsByFromTeamIdIn(myTeamIds);
    }

    private List<Long> getMyTeamIds(Long memberId) {
       return teamMemberPort.findTeamMembersByMemberId(memberId)
                      .getTeamMembers()
                      .stream()
                      .map(TeamMember::getTeamId)
                      .collect(
                          Collectors.toUnmodifiableList());
    }
}
