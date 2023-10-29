package com.everyTing.team.application.service;

import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_005;
import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_015;
import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_027;
import static com.everyTing.team.common.exception.errorCode.TeamServerErrorCode.TSER_008;
import static com.everyTing.team.common.util.UniqueCodeGenerator.generateCode;

import com.everyTing.core.exception.TingApplicationException;
import com.everyTing.core.exception.TingServerException;
import com.everyTing.core.feign.dto.Member;
import com.everyTing.team.adapter.out.persistence.entity.data.Code;
import com.everyTing.team.adapter.out.persistence.entity.data.Major;
import com.everyTing.team.adapter.out.persistence.entity.data.Name;
import com.everyTing.team.adapter.out.persistence.entity.data.University;
import com.everyTing.team.application.port.in.TeamUseCase;
import com.everyTing.team.application.port.in.command.TeamFindByCodeCommand;
import com.everyTing.team.application.port.in.command.TeamFindByIdCommand;
import com.everyTing.team.application.port.in.command.TeamRemoveCommand;
import com.everyTing.team.application.port.in.command.TeamSaveCommand;
import com.everyTing.team.application.port.out.MemberPort;
import com.everyTing.team.application.port.out.TeamMemberPort;
import com.everyTing.team.application.port.out.TeamPort;
import com.everyTing.team.domain.Team;
import com.everyTing.team.domain.TeamMember;
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
        if (teamMemberPort.existsTeamLeaderByMemberId(command.getMemberId())) {
            throw new TingApplicationException(TEAM_005);
        }

        Member member = memberPort.getMemberById(command.getMemberId());

        final Code code = getUniqueCode(command.getName());
        Long savedTeamId = teamPort.saveTeam(
            member.getId(), command.getName(), command.getRegions(),
            University.from(member.getUniversity()), Major.from(member.getMajor()),
            code, command.getMemberLimit(), member.getGender(),
            command.getHashtags());

        teamMemberPort.saveTeamLeader(savedTeamId, member.getId());

        return savedTeamId;
    }

    private Code getUniqueCode(Name name) {
        final String nameWithoutSpaces = name.getValue()
                                             .replace(" ", "");

        for (int i = 0; i < 10 ; i++) {
            final Code code = Code.from(nameWithoutSpaces + "_" + generateCode());
            if (!teamPort.existsTeamByCode(code)) {
                return code;
            }
        }
        throw new TingServerException(TSER_008);
    }

    @Override
    @Transactional
    public void removeTeam(TeamRemoveCommand command) {
        TeamMember teamLeader = teamMemberPort.findTeamLeader(command.getTeamId());
        validateMemberIsTeamLeader(teamLeader, command.getMemberId());

        final Team team = teamPort.findTeamById(command.getTeamId());
        validateTeamHasOnlyOneMember(team);

        teamPort.removeTeam(command.getTeamId());
    }

    private void validateMemberIsTeamLeader(TeamMember leader, Long memberId) {
        if (leader.getMemberId() != memberId) {
            throw new TingApplicationException(TEAM_015);
        }
    }

    private void validateTeamHasOnlyOneMember(Team team) {
        if (team.getMemberNumber() > 1) { // 팀장 한 명만 남았는지 확인합니다.
            throw new TingApplicationException(TEAM_027);
        }
    }
}
