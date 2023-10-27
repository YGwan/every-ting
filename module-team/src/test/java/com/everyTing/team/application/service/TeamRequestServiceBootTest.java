package com.everyTing.team.application.service;

import static com.everyTing.team.common.constraints.TeamConstraints.DAILY_REQUEST_LIMIT;
import static com.everyTing.team.utils.TeamEntityFixture.code;
import static com.everyTing.team.utils.TeamEntityFixture.major;
import static com.everyTing.team.utils.TeamEntityFixture.memberLimit;
import static com.everyTing.team.utils.TeamEntityFixture.name;
import static com.everyTing.team.utils.TeamEntityFixture.university;
import static org.assertj.core.api.Assertions.assertThat;

import com.everyTing.core.domain.Gender;
import com.everyTing.core.exception.TingApplicationException;
import com.everyTing.team.adapter.out.persistence.entity.TeamEntity;
import com.everyTing.team.adapter.out.persistence.entity.TeamMemberEntity;
import com.everyTing.team.adapter.out.persistence.entity.TeamRequestEntity;
import com.everyTing.team.adapter.out.persistence.entity.data.Code;
import com.everyTing.team.adapter.out.persistence.entity.data.Role;
import com.everyTing.team.adapter.out.persistence.repository.TeamEntityRepository;
import com.everyTing.team.adapter.out.persistence.repository.TeamMemberEntityRepository;
import com.everyTing.team.adapter.out.persistence.repository.TeamRequestEntityRepository;
import com.everyTing.team.application.port.in.command.TeamRequestSaveCommand;
import com.everyTing.team.application.port.out.TeamRequestPort;
import java.time.LocalDate;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class TeamRequestServiceBootTest {

    @Autowired
    private TeamRequestService teamRequestService;
    @Autowired
    private TeamRequestEntityRepository teamRequestEntityRepository;
    @Autowired
    private TeamEntityRepository teamEntityRepository;
    @Autowired
    private TeamMemberEntityRepository teamMemberEntityRepository;

    private TeamEntity fromTeam;
    private TeamEntity toTeam;
    private TeamMemberEntity fromTeamLeader;

    @BeforeEach
    public void insert() {
        fromTeam = TeamEntity.of(name, university, major, code, memberLimit,
            Gender.FEMALE);
        fromTeam.increaseMemberNumber();
        fromTeam.increaseMemberNumber();
        toTeam = TeamEntity.of(name, university, major, Code.from("newnew"), memberLimit,
            Gender.MALE);
        toTeam.increaseMemberNumber();
        toTeam.increaseMemberNumber();
        teamEntityRepository.save(fromTeam);
        teamEntityRepository.save(toTeam);
        fromTeamLeader = teamMemberEntityRepository.save(
            TeamMemberEntity.of(fromTeam.getId(), 1L, Role.LEADER));
    }

    @DisplayName("매칭 요청 - 동시에 100팀에 요청 버튼을 눌렀을 때")
    @Disabled
    public void saveTeamRequest() throws InterruptedException {
        int threadCount = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        CountDownLatch latch = new CountDownLatch(threadCount);

        TeamRequestSaveCommand command = TeamRequestSaveCommand.of(fromTeam.getId(), toTeam.getId(),
            fromTeamLeader.getMemberId());

        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                try {
                    teamRequestService.saveTeamRequest(command);
                } catch (TingApplicationException e){
                    System.out.println("e.getErrorCode() = " + e.getErrorCode());
                }finally {
                    latch.countDown();
                }
            });
        }

        latch.await();

        Long fromTeamRequestCount = teamRequestEntityRepository.countByFromTeamIdAndCreatedAtAfter(fromTeam.getId(), LocalDate.now().atStartOfDay());

        assertThat(fromTeamRequestCount).isEqualTo(DAILY_REQUEST_LIMIT);
    }
}
