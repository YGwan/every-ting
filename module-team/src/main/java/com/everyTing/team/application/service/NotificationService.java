package com.everyTing.team.application.service;

import com.everyTing.core.feign.dto.Member;
import com.everyTing.team.application.port.in.command.MyTeamRemoveCommand;
import com.everyTing.team.application.port.in.command.TeamMemberRemoveCommand;
import com.everyTing.team.application.port.in.command.TeamMemberSaveCommand;
import com.everyTing.team.application.port.in.command.TeamRequestSaveCommand;
import com.everyTing.team.application.port.out.MemberPort;
import com.everyTing.team.application.port.out.NotificationPort;
import com.everyTing.team.application.port.out.TeamDatePort;
import com.everyTing.team.application.port.out.TeamMemberPort;
import com.everyTing.team.application.port.out.TeamPort;
import com.everyTing.team.common.notificationForm.DateMatchedNotificationForm;
import com.everyTing.team.common.notificationForm.DateRequestRejectedNotificationForm;
import com.everyTing.team.common.notificationForm.DateRequestedNotificationForm;
import com.everyTing.team.common.notificationForm.NewTeamMemberNotificationForm;
import com.everyTing.team.common.notificationForm.TeamMemberExpelledNotificationForm;
import com.everyTing.team.common.notificationForm.TeamMemberLeftNotificationForm;
import com.everyTing.team.domain.Team;
import com.everyTing.team.domain.TeamDateWithGender;
import com.everyTing.team.domain.TeamMember;
import com.everyTing.team.domain.TeamRequest;
import java.util.List;
import java.util.stream.Collectors;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Aspect
@Async
@Component
public class NotificationService {

    private final NotificationPort notificationPort;
    private final TeamPort teamPort;
    private final TeamMemberPort teamMemberPort;
    private final MemberPort memberPort;
    private final TeamDatePort teamDatePort;

    public NotificationService(NotificationPort notificationPort, TeamPort teamPort,
        TeamMemberPort teamMemberPort, MemberPort memberPort, TeamDatePort teamDatePort) {
        this.notificationPort = notificationPort;
        this.teamPort = teamPort;
        this.teamMemberPort = teamMemberPort;
        this.memberPort = memberPort;
        this.teamDatePort = teamDatePort;
    }

    @AfterReturning(
        pointcut = "execution(* com.everyTing.team.application.service.TeamDateService.saveTeamDate(..))",
        returning = "savedDateId")
    public void sendNotificationWhenDateMatched(Long savedDateId) {
        final TeamDateWithGender teamDate = teamDatePort.findTeamDate(savedDateId);

        final Team menTeam = teamPort.findTeamById(teamDate.getMenTeamId());
        final Team womenTeam = teamPort.findTeamById(teamDate.getWomenTeamId());

        final List<Long> fromTeamMemberIds = getTeamMemberIds(menTeam.getId());
        notificationPort.sendNotification(fromTeamMemberIds,
            DateMatchedNotificationForm.of(savedDateId, menTeam.getName(), womenTeam.getName()));

        final List<Long> toTeamMemberIds = getTeamMemberIds(womenTeam.getId());
        notificationPort.sendNotification(toTeamMemberIds,
            DateMatchedNotificationForm.of(savedDateId, womenTeam.getName(), menTeam.getName()));
    }

    private List<Long> getTeamMemberIds(Long teamId) {
        return teamMemberPort.findTeamMembersByTeamId(teamId)
                             .getTeamMembers()
                             .stream()
                             .map(TeamMember::getMemberId)
                             .collect(Collectors.toUnmodifiableList());
    }

    @AfterReturning(
        pointcut = "execution(* com.everyTing.team.application.service.TeamRequestService.saveTeamRequest(..))",
        returning = "savedRequestId")
    public void sendNotificationWhenDateRequested(JoinPoint joinPoint, Long savedRequestId) {
        final Object[] args = joinPoint.getArgs();
        final TeamRequestSaveCommand command = (TeamRequestSaveCommand) args[0];

        final Long targetMemberId = teamMemberPort.findTeamLeader(command.getToTeamId()).getMemberId();
        final String toTeamName = teamPort.findTeamById(command.getToTeamId())
                                          .getName();

        notificationPort.sendNotification(targetMemberId, DateRequestedNotificationForm.of(savedRequestId, toTeamName));
    }

    @AfterReturning("execution(* com.everyTing.team.application.service.internal.TeamRequestInternalService.rejectTeamRequest(..))")
    public void sendNotificationWhenDateRequestRejected(JoinPoint joinPoint) {
        final Object[] args = joinPoint.getArgs();
        final TeamRequest request = (TeamRequest) args[0];

        System.out.println("Sending notification for rejected team request: " + request.getId());

        final Team rejectedTeam = teamPort.findTeamById(request.getFromTeamId());
        final Long rejectedTeamLeaderId = teamMemberPort.findTeamLeader(request.getFromTeamId())
                                                        .getMemberId();
        notificationPort.sendNotification(rejectedTeamLeaderId,
            DateRequestRejectedNotificationForm.of(request.getId(), rejectedTeam.getName()));
    }

    @AfterReturning("execution(* com.everyTing.team.application.service.TeamMemberService.saveTeamMember(..))")
    public void sendNotificationWhenNewTeamMemberJoined(JoinPoint joinPoint) {
        final Object[] args = joinPoint.getArgs();
        final TeamMemberSaveCommand command = (TeamMemberSaveCommand) args[0];

        final Member member = memberPort.getMemberById(command.getMemberId());
        final Team team = teamPort.findTeamById(command.getTeamId());
        final Long teamLeaderId = teamMemberPort.findTeamLeader(command.getTeamId()).getMemberId();

        notificationPort.sendNotification(teamLeaderId,
            NewTeamMemberNotificationForm.of(command.getTeamId(), team.getName(), member.getUsername()));
    }

    @AfterReturning("execution(* com.everyTing.team.application.service.TeamMemberService.removeTeamMember(..))")
    public void sendNotificationWhenTeamMemberExpelled(JoinPoint joinPoint) {
        final Object[] args = joinPoint.getArgs();
        final TeamMemberRemoveCommand teamMember = (TeamMemberRemoveCommand) args[0];

        final Long targetMemberId = teamMember.getMemberId();
        final Team team = teamPort.findTeamById(teamMember.getTeamId());

        notificationPort.sendNotification(targetMemberId,
            TeamMemberExpelledNotificationForm.of(team.getName()));
    }

    @AfterReturning("execution(* com.everyTing.team.application.service.MyTeamService.removeMyTeam(..))")
    public void sendNotificationWhenTeamMemberLeft(JoinPoint joinPoint) {
        final Object[] args = joinPoint.getArgs();
        final MyTeamRemoveCommand command = (MyTeamRemoveCommand) args[0];

        final Long targetMemberId = teamMemberPort.findTeamLeader(command.getTeamId()).getMemberId();
        final Team team = teamPort.findTeamById(command.getTeamId());
        final Member exitingMember = memberPort.getMemberById(command.getMemberId());

        notificationPort.sendNotification(targetMemberId,
            TeamMemberLeftNotificationForm.of(team.getId(), team.getName(),
                exitingMember.getUsername()));
    }
}
