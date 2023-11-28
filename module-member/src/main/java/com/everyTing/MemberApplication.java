package com.everyTing;

import com.everyTing.member.service.member.MemberService;
import com.everyTing.member.utils.dummy.MemberDummyData;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;

@EnableCaching
@SpringBootApplication
public class MemberApplication {

    public static void main(String[] args) {
        System.setProperty("spring.config.name", "application, application-core");
        ApplicationContext context = SpringApplication.run(MemberApplication.class, args);

        MemberService memberService = context.getBean(MemberService.class);
        MemberDummyData memberDummyData = new MemberDummyData(memberService);
        memberDummyData.createMember();
    }
}
