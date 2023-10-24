package com.everyTing.team.application.service;

import static com.everyTing.team.common.constraints.TeamConstraints.WEEKLY_DATE_LIMIT;
import static com.everyTing.team.utils.TeamEntityFixture.code;
import static com.everyTing.team.utils.TeamEntityFixture.major;
import static com.everyTing.team.utils.TeamEntityFixture.memberLimit;
import static com.everyTing.team.utils.TeamEntityFixture.name;
import static com.everyTing.team.utils.TeamEntityFixture.university;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.everyTing.core.domain.Gender;
import com.everyTing.core.exception.TingApplicationException;
import com.everyTing.team.adapter.out.persistence.entity.TeamEntity;
import com.everyTing.team.adapter.out.persistence.entity.TeamMemberEntity;
import com.everyTing.team.adapter.out.persistence.entity.TeamRequestEntity;
import com.everyTing.team.adapter.out.persistence.entity.data.Code;
import com.everyTing.team.adapter.out.persistence.entity.data.Role;
import com.everyTing.team.adapter.out.persistence.repository.TeamDateEntityRepository;
import com.everyTing.team.adapter.out.persistence.repository.TeamEntityRepository;
import com.everyTing.team.adapter.out.persistence.repository.TeamMemberEntityRepository;
import com.everyTing.team.application.port.in.command.TeamDateSaveCommand;
import com.everyTing.team.application.port.out.TeamRequestPort;
import com.everyTing.team.domain.TeamRequest;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class TeamDateServiceBootTest {

    @Autowired
    private TeamDateService teamDateService;
    @Autowired
    private TeamDateEntityRepository teamDateEntityRepository;
    @Autowired
    private TeamEntityRepository teamEntityRepository;
    @Autowired
    private TeamMemberEntityRepository teamMemberEntityRepository;
    @MockBean
    private TeamRequestPort teamRequestPort;

    private TeamEntity womenTeam;
    private TeamEntity menTeam;
    private TeamMemberEntity menTeamLeader;

    @BeforeEach
    public void insert() {
        womenTeam = TeamEntity.of(name, university, major, code, memberLimit,
            Gender.FEMALE);
        womenTeam.increaseMemberNumber();
        womenTeam.increaseMemberNumber();
        menTeam = TeamEntity.of(name, university, major, Code.from("newnew"), memberLimit,
            Gender.MALE);
        menTeam.increaseMemberNumber();
        menTeam.increaseMemberNumber();
        teamEntityRepository.save(womenTeam);
        teamEntityRepository.save(menTeam);
        menTeamLeader = teamMemberEntityRepository.save(
            TeamMemberEntity.of(menTeam.getId(), 1L, Role.LEADER));
    }

    @AfterEach
    public void delete() {
        teamEntityRepository.delete(womenTeam);
        teamEntityRepository.delete(menTeam);
        teamMemberEntityRepository.delete(menTeamLeader);
    }

    @DisplayName("미팅 매칭 - 동시에 100 팀이 수락 버튼을 눌렀을 때")
    @Disabled
    public void saveTeamDate() throws InterruptedException {
        int threadCount = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        CountDownLatch latch = new CountDownLatch(threadCount);

        TeamDateSaveCommand command = TeamDateSaveCommand.of(1L,
            menTeamLeader.getMemberId());


        given(teamRequestPort.findTeamRequest(any())).willReturn(TeamRequest.from(TeamRequestEntity.of(womenTeam, menTeam)));

        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                try {
                    teamDateService.saveTeamDate(command);
                } catch (TingApplicationException e){
                    System.out.println("e.getErrorCode() = " + e.getErrorCode());
                }finally {
                    latch.countDown();
                }
            });
        }

        latch.await();

        Long womenTeamDateCount = teamDateEntityRepository.countByWomenTeamIdOrMenTeamId(womenTeam.getId(),
            womenTeam.getId());
        Long menTeamDateCount = teamDateEntityRepository.countByWomenTeamIdOrMenTeamId(menTeam.getId(), menTeam.getId());

        assertThat(womenTeamDateCount).isEqualTo(WEEKLY_DATE_LIMIT);
        assertThat(menTeamDateCount).isEqualTo(WEEKLY_DATE_LIMIT);
    }
}
