package com.everyTing.team.application.service;

import static com.everyTing.team.utils.TeamEntityFixture.code;
import static com.everyTing.team.utils.TeamEntityFixture.gender;
import static com.everyTing.team.utils.TeamEntityFixture.major;
import static com.everyTing.team.utils.TeamEntityFixture.name;
import static com.everyTing.team.utils.TeamEntityFixture.university;
import static com.everyTing.team.utils.TeamRegionEntityFixture.regions;
import static org.assertj.core.api.Assertions.assertThat;

import com.everyTing.core.exception.TingApplicationException;
import com.everyTing.team.adapter.out.persistence.entity.data.MemberLimit;
import com.everyTing.team.adapter.out.persistence.repository.TeamEntityRepository;
import com.everyTing.team.adapter.out.persistence.repository.TeamMemberEntityRepository;
import com.everyTing.team.application.port.in.command.TeamMemberSaveCommand;
import com.everyTing.team.application.port.out.TeamMemberPort;
import com.everyTing.team.application.port.out.TeamPort;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@DisplayName("팀 멤버 service boot test")
@SpringBootTest
class TeamMemberServiceBootTest {

    @Autowired
    private TeamMemberPort teamMemberPort;
    @Autowired
    private TeamMemberService teamMemberService;
    @Autowired
    private TeamPort teamPort;
    @Autowired
    private TeamEntityRepository teamEntityRepository;
    @Autowired
    private TeamMemberEntityRepository teamMemberEntityRepository;

    private Long createdTeamId;
    private Long createdTeamMemberId;

    @BeforeEach
    public void insert() {
        createdTeamId = teamPort.saveTeam(1L, name, regions, university, major, code,
            MemberLimit.from((short) 6), gender, List.of());
        createdTeamMemberId = teamMemberPort.saveTeamLeader(createdTeamId, 1L);
    }

    @AfterEach
    public void delete() {
        teamEntityRepository.deleteById(createdTeamId);
        teamMemberEntityRepository.deleteById(createdTeamMemberId);
    }

    @DisplayName("팀 멤버 저장 - 동시에 100명이 가입하려고 할 때")
    @Disabled
    void saveTeamMember() throws InterruptedException {
        int threadCount = 100;
        AtomicLong memberId = new AtomicLong(1L);
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                try {
                    teamMemberService.saveTeamMember(
                        TeamMemberSaveCommand.of(createdTeamId, memberId.getAndIncrement()));
                } catch (TingApplicationException e) {
                    System.out.println("e.getErrorCode() = " + e.getErrorCode());
                } finally {
                    latch.countDown();
                }
            });

        }

        latch.await();

        int memberNumber = teamEntityRepository.findById(createdTeamId)
                                               .orElseThrow()
                                               .getMemberNumber();
        Long count = teamMemberEntityRepository.countByTeamId(createdTeamId);

        assertThat(memberNumber).isEqualTo(6);
        assertThat(count).isEqualTo(6);
    }
}
