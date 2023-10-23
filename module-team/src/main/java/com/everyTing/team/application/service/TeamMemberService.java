package com.everyTing.team.application.service;

import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_015;
import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_026;

import com.everyTing.core.exception.TingApplicationException;
import com.everyTing.core.feign.dto.Member;
import com.everyTing.team.application.port.in.TeamMemberUseCase;
import com.everyTing.team.application.port.in.command.TeamMemberFindCommand;
import com.everyTing.team.application.port.in.command.TeamMemberRemoveCommand;
import com.everyTing.team.application.port.in.command.TeamMemberSaveCommand;
import com.everyTing.team.application.port.out.MemberPort;
import com.everyTing.team.application.port.out.TeamMemberPort;
import com.everyTing.team.domain.TeamMember;
import com.everyTing.team.domain.TeamMembers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class TeamMemberService implements TeamMemberUseCase {

    private final TeamMemberPort teamMemberPort;
    private final MemberPort memberPort;

    public TeamMemberService(TeamMemberPort teamMemberPort, MemberPort memberPort) {
        this.teamMemberPort = teamMemberPort;
        this.memberPort = memberPort;
    }

    @Override
    public TeamMembers findTeamMembers(TeamMemberFindCommand command) {
        return teamMemberPort.findTeamMembersByTeamId(command.getTeamId());
    }

    @Override
    @Transactional
    public Long saveTeamMember(TeamMemberSaveCommand command) {
        final Member member = memberPort.getMemberById(command.getMemberId());

        return teamMemberPort.saveTeamMember(command.getTeamId(), member);
    }

    @Override
    @Transactional
    public void removeTeamMember(TeamMemberRemoveCommand command) {
        final TeamMember teamLeader = teamMemberPort.findTeamLeader(command.getTeamId());

        validateMemberIsTeamLeader(command.getMemberId(), teamLeader);
        validateTeamMemberToBeRemovedIsNotTeamLeader(command.getTeamMemberId(), teamLeader);

        teamMemberPort.removeTeamMember(command.getTeamId(), command.getTeamMemberId());
    }

    private void validateMemberIsTeamLeader(Long memberId, TeamMember teamLeader) {
        if (teamLeader.getMemberId() != memberId) {
            throw new TingApplicationException(TEAM_015);
        }
    }

    private void validateTeamMemberToBeRemovedIsNotTeamLeader(
        Long teamMemberId, TeamMember teamLeader) {
        if (teamMemberId == teamLeader.getTeamMemberId()) {
            throw new TingApplicationException(TEAM_026);
        }
    }
}
