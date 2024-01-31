package com.everyTing.member.init;

import com.everyTing.core.exception.TingServerException;
import com.everyTing.member.service.member.MemberService;
import com.everyTing.member.utils.dummy.MemberDummyData;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import static com.everyTing.member.errorCode.MemberServerErrorCode.MSER_006;

@Component
public class CreateDummyData implements CommandLineRunner {

    private final MemberService memberService;

    public CreateDummyData(MemberService memberService) {
        this.memberService = memberService;
    }

    @Override
    public void run(String... args) {
        try {
            MemberDummyData memberDummyData = new MemberDummyData(memberService);
            memberDummyData.createMember();
        } catch (Exception e) {
            throw new TingServerException(MSER_006);
        }
    }
}
