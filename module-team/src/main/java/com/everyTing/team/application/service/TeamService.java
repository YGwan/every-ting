package com.everyTing.team.application.service;

import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_005;

import com.everyTing.core.exception.TingApplicationException;
import com.everyTing.core.feign.dto.Member;
import com.everyTing.team.adapter.out.persistence.entity.data.Code;
import com.everyTing.team.adapter.out.persistence.entity.data.Major;
import com.everyTing.team.adapter.out.persistence.entity.data.Role;
import com.everyTing.team.adapter.out.persistence.entity.data.University;
import com.everyTing.team.application.port.in.TeamUseCase;
import com.everyTing.team.application.port.in.command.TeamFindByCodeCommand;
import com.everyTing.team.application.port.in.command.TeamFindByIdCommand;
import com.everyTing.team.application.port.in.command.TeamSaveCommand;
import com.everyTing.team.application.port.out.MemberPort;
import com.everyTing.team.application.port.out.TeamMemberPort;
import com.everyTing.team.application.port.out.TeamPort;
import com.everyTing.team.domain.Team;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class TeamService implements TeamUseCase {

    private final TeamPort teamPort;
    private final MemberPort memberPort;
    private final TeamMemberPort teamMemberPort;

    public TeamService(TeamPort teamPort, MemberPort memberPort, TeamMemberPort teamMemberPort) {
        this.teamPort = teamPort;
        this.memberPort = memberPort;
        this.teamMemberPort = teamMemberPort;
    }

    @Override
    public Team findTeamById(TeamFindByIdCommand command) {
        return teamPort.findTeamById(command.getTeamId());
    }

    @Override
    public Team findTeamByCode(TeamFindByCodeCommand command) {
        return teamPort.findTeamByCode(command.getCode());
    }

    @Override
    @Transactional
    public Long saveTeam(TeamSaveCommand command) {
        if (teamMemberPort.existsTeamMemberByTeamLeaderId(command.getMemberId())) {
            throw new TingApplicationException(TEAM_005);
        }

        Member member = memberPort.getMemberById(command.getMemberId());

        final String teamCode = generateCode();
        Long savedTeamId = teamPort.saveTeam(
            member.getMemberId(), command.getName(), command.getRegions(),
            University.from(member.getUniversity()), Major.from(member.getMajor()),
            Code.from(teamCode), command.getMemberLimit(), member.getGender(),
            command.getHashtags());

        teamMemberPort.saveTeamLeader(savedTeamId, member.getMemberId());

        return savedTeamId;
    }
}
