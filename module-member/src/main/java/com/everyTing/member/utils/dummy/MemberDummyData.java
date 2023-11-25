package com.everyTing.member.utils.dummy;

import com.everyTing.core.domain.Gender;
import com.everyTing.member.dto.request.SignUpRequest;
import com.everyTing.member.service.MemberService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class MemberDummyData {

    private final MemberService memberService;

    public MemberDummyData(MemberService memberService) {
        this.memberService = memberService;
    }

    @Transactional
    public void createMember() {

        //여자 더미 데이터 - 단국대 10
        memberService.signUp(
                new SignUpRequest("리나", Gender.FEMALE, 1998, "32190001@dankook.ac.kr", "qwer1234!",
                        "luckyStar123", "단국대학교", "컴퓨터공학과", "https://jin749.site/static/sample/female/female2.png")
        );
        memberService.signUp(
                new SignUpRequest("솔라", Gender.FEMALE, 1998, "32190002@dankook.ac.kr", "qwer1234!",
                        "dreamCatcher92", "단국대학교", "컴퓨터공학과", "https://jin749.site/static/sample/female/female3.png")
        );
        memberService.signUp(
                new SignUpRequest("유나", Gender.FEMALE, 1998, "32190003@dankook.ac.kr", "qwer1234!",
                        "blazingFireworks", "단국대학교", "소프트웨어학과", "https://jin749.site/static/sample/female/female5.png")
        );
        memberService.signUp(
                new SignUpRequest("지수", Gender.FEMALE, 1999, "32190004@dankook.ac.kr", "qwer1234!",
                        "DanTest1", "단국대학교", "소프트웨어학과", "https://jin749.site/static/sample/female/female27.png")
        );
        memberService.signUp(
                new SignUpRequest("리사", Gender.FEMALE, 1999, "32190005@dankook.ac.kr", "qwer1234!",
                        "DanTest2", "단국대학교", "모바일시스템공학과", "https://jin749.site/static/sample/female/female31.png")
        );
        memberService.signUp(
                new SignUpRequest("로제", Gender.FEMALE, 1999, "32190006@dankook.ac.kr", "qwer1234!",
                        "DanTest3", "단국대학교", "모바일시스템공학과", "https://jin749.site/static/sample/female/female38.png")
        );
        memberService.signUp(
                new SignUpRequest("나연", Gender.FEMALE, 2000, "32200007@dankook.ac.kr", "qwer1234!",
                        "DanTest4", "단국대학교", "산업보안학과", "https://jin749.site/static/sample/female/female39.png")
        );
        memberService.signUp(
                new SignUpRequest("사나", Gender.FEMALE, 2000, "32200008@dankook.ac.kr", "qwer1234!",
                        "DanTest5", "단국대학교", "모바일시스템공학과", "https://jin749.site/static/sample/female/female40.png")
        );
        memberService.signUp(
                new SignUpRequest("나은", Gender.FEMALE, 2000, "32200009@dankook.ac.kr", "qwer1234!",
                        "DanTest6", "단국대학교", "경영학과", "https://jin749.site/static/sample/female/female41.png")
        );
        memberService.signUp(
                new SignUpRequest("은하", Gender.FEMALE, 2001, "32210010@dankook.ac.kr", "qwer1234!",
                        "DanTest7", "단국대학교", "경영학과", "https://jin749.site/static/sample/female/female42.png")
        );

        //여자 더미 데이터 - 경기대 4
        memberService.signUp(
                new SignUpRequest("아이린", Gender.FEMALE, 2001, "32180001@kyonggi.ac.kr", "qwer1234!",
                        "midnightWhisperer", "경기대학교", "신소재공학과", "https://jin749.site/static/sample/female/female7.png")
        );
        memberService.signUp(
                new SignUpRequest("조이", Gender.FEMALE, 1999, "32180002@kyonggi.ac.kr", "qwer1234!",
                        "cosmicExplorer", "경기대학교", "신소재공학과", "https://jin749.site/static/sample/female/female15.png")
        );
        memberService.signUp(
                new SignUpRequest("정연", Gender.FEMALE, 1998, "32180003@kyonggi.ac.kr", "qwer1234!",
                        "secretAgentX", "경기대학교", "신소재공학과", "https://jin749.site/static/sample/female/female18.png")
        );
        memberService.signUp(
                new SignUpRequest("미연", Gender.FEMALE, 1998, "32180004@kyonggi.ac.kr", "qwer1234!",
                        "Wminnis9", "경기대학교", "신소재공학과", "https://jin749.site/static/sample/female/female50.png")
        );

        // 여자 더미 데이터 - 중앙대 2
        memberService.signUp(
                new SignUpRequest("미나", Gender.FEMALE, 1998, "32170001@cau.ac.kr", "qwer1234!",
                        "WCAU1",  "중앙대학교", "경영학과", "https://jin749.site/static/sample/female/female20.png")
        );
        memberService.signUp(
                new SignUpRequest("다현", Gender.FEMALE, 1998, "32170002@cau.ac.kr", "qwer1234!",
                        "WCau2", "중앙대학교", "경영학과", "https://jin749.site/static/sample/female/female25.png")
        );

        // 여자 더미 데이터 - 서울대 2
        memberService.signUp(
                new SignUpRequest("우기", Gender.FEMALE, 1998, "32180001@snu.ac.kr", "qwer1234!",
                        "WSnu1", "서울대학교", "컴퓨터공학과", "https://jin749.site/static/sample/female/female51.png")
        );
        memberService.signUp(
                new SignUpRequest("소연", Gender.FEMALE, 1998, "32180002@snu.ac.kr", "qwer1234!",
                        "WSnu12", "서울대학교", "컴퓨터공학과", "https://jin749.site/static/sample/female/female52.png")
        );

        // 남자 더미 데이터 - 단국대 10명
        memberService.signUp(
                new SignUpRequest("지민", Gender.MALE, 1998, "32181001@dankook.ac.kr", "qwer1234!",
                        "MDantest1", "단국대학교", "컴퓨터공학과", "https://jin749.site/static/sample/male/male2.png")
        );
        memberService.signUp(
                new SignUpRequest("정국", Gender.MALE, 1998, "32181002@dankook.ac.kr", "qwer1234!",
                        "MDantest2", "단국대학교", "컴퓨터공학과", "https://jin749.site/static/sample/male/male3.png")
        );
        memberService.signUp(
                new SignUpRequest("수호", Gender.MALE, 1998, "32181003@dankook.ac.kr", "qwer1234!",
                        "MDantest3", "단국대학교", "소프트웨어학과", "https://jin749.site/static/sample/male/male5.png")
        );
        memberService.signUp(
                new SignUpRequest("레이", Gender.MALE, 1999, "32191004@dankook.ac.kr", "qwer1234!",
                        "MDantest4", "단국대학교", "소프트웨어학과", "https://jin749.site/static/sample/male/male7.png")
        );
        memberService.signUp(
                new SignUpRequest("백현", Gender.MALE, 1999, "32191005@dankook.ac.kr", "qwer1234!",
                        "MDantest5", "단국대학교", "모바일시스템공학과", "https://jin749.site/static/sample/male/male10.png")
        );
        memberService.signUp(
                new SignUpRequest("디오", Gender.MALE, 1999, "32191006@dankook.ac.kr", "qwer1234!",
                        "MDantest6", "단국대학교", "모바일시스템공학과", "https://jin749.site/static/sample/male/male11.png")
        );
        memberService.signUp(
                new SignUpRequest("카이", Gender.MALE, 2000, "32201007@dankook.ac.kr", "qwer1234!",
                        "MDantest7", "단국대학교", "산업보안학과", "https://jin749.site/static/sample/male/male12.png")
        );
        memberService.signUp(
                new SignUpRequest("세훈", Gender.MALE, 2000, "32201008@dankook.ac.kr", "qwer1234!",
                        "MDantest8", "단국대학교", "산업보안학과", "https://jin749.site/static/sample/male/male13.png")
        );
        memberService.signUp(
                new SignUpRequest("마크", Gender.MALE, 2000, "32201009@dankook.ac.kr", "qwer1234!",
                        "MDantest9", "단국대학교", "경영학과", "https://jin749.site/static/sample/male/male15.png")
        );
        memberService.signUp(
                new SignUpRequest("유겸", Gender.MALE, 2001, "32201010@dankook.ac.kr", "qwer1234!",
                        "MDantest10", "단국대학교", "경영학과", "https://jin749.site/static/sample/male/male16.png")
        );

        // 남자 더미데이터 - 경기대 남자 4
        memberService.signUp(
                new SignUpRequest("제이키", Gender.MALE, 2001, "32201001@kyonggi.ac.kr", "qwer1234!",
                        "MKyongi1", "경기대학교", "신소재공학과", "https://jin749.site/static/sample/male/male17.png")
        );
        memberService.signUp(
                new SignUpRequest("우지", Gender.MALE, 1999, "32181002@kyonggi.ac.kr", "qwer1234!",
                        "MKyongi2", "경기대학교", "신소재공학과", "https://jin749.site/static/sample/male/male18.png")
        );
        memberService.signUp(
                new SignUpRequest("민규", Gender.MALE, 1998, "32181003@kyonggi.ac.kr", "qwer1234!",
                        "MKyongi3", "경기대학교", "경찰행정학과", "https://jin749.site/static/sample/male/male19.png")
        );
        memberService.signUp(
                new SignUpRequest("태일", Gender.MALE, 1998, "32181004@kyonggi.ac.kr", "qwer1234!",
                        "MKyongi4", "경기대학교", "경찰행정학과", "https://jin749.site/static/sample/male/male20.png")
        );

        // 남자 더미데이터 - 중앙대 남자 2
        memberService.signUp(
                new SignUpRequest("신동", Gender.MALE, 2000, "32191001@cau.ac.kr", "qwer1234!",
                        "MCau1", "중앙대학교", "컴퓨터공학과", "https://jin749.site/static/sample/male/male21.png")
        );
        memberService.signUp(
                new SignUpRequest("지성", Gender.MALE, 2000, "32191002@cau.ac.kr", "qwer1234!",
                        "MCau2", "중앙대학교", "컴퓨터공학과", "https://jin749.site/static/sample/male/male22.png")
        );

        // 남자 더미데이터 - 서울대 남자 2
        memberService.signUp(
                new SignUpRequest("재민", Gender.MALE, 2000, "32191001@snu.ac.kr", "qwer1234!",
                        "MSnu1", "서울대학교", "컴퓨터공학과", "https://jin749.site/static/sample/male/male24.png")
        );
        memberService.signUp(
                new SignUpRequest("해찬", Gender.MALE, 2000, "32201002@snu.ac.kr", "qwer1234!",
                        "MSnu2", "서울대학교", "컴퓨터공학과", "https://jin749.site/static/sample/male/male25.png")
        );
    }
}
