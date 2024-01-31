package com.everyTing.member.init;

import com.everyTing.member.repository.MemberRepository;
import com.everyTing.member.service.member.MemberService;
import com.everyTing.member.utils.dummy.MemberDummyData;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InitHandler implements CommandLineRunner {

    private final MemberRepository memberRepository;
    private final MemberService memberService;

    public InitHandler(MemberRepository memberRepository, MemberService memberService) {
        this.memberRepository = memberRepository;
        this.memberService = memberService;
    }

    @Override
    public void run(String... args) {
        createDummyData();
        cacheWarmUp();
    }

    private void createDummyData() {
        MemberDummyData memberDummyData = new MemberDummyData(memberService);
        memberDummyData.createMember();
    }

    private void cacheWarmUp() {
        List<Long> top50MemberIdsSaveRecently = memberRepository.findIdsTop50ByOrderByUpdatedAtDesc();
        for (Long id : top50MemberIdsSaveRecently) {
            memberService.findMemberInfo(id);
        }
    }
}
