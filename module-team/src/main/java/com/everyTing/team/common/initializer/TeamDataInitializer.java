package com.everyTing.team.common.initializer;

import com.everyTing.team.application.port.in.TeamMemberUseCase;
import com.everyTing.team.application.port.in.TeamUseCase;
import com.everyTing.team.application.port.in.command.TeamMemberSaveCommand;
import com.everyTing.team.application.port.in.command.TeamSaveCommand;
import java.util.List;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class TeamDataInitializer implements ApplicationRunner {

    private static Integer FEMALE_DUMMY_START_INDEX = 1;
    private static Integer FEMALE_DUMMY_END_INDEX = 18;
    private static Integer MALE_DUMMY_START_INDEX = 19;
    private static Integer MALE_DUMMY_END_INDEX = 36;
    private static List<String> TEAM_NAME = List.of(
        "후라이의 꿈", "보드게임 하는", "크리스마스에 미팅", "롤하는", "TMT 모집",
        "파이팅 해야지", "심심해", "금요일에 만나요", "어푸", "12월의 기적",
        "피카부", "음파음파", "친구라도", "에버랜드 가는 모임", "E들의 모임",
        "I들의 모임", "냥냥", "에브리팅 멋쟁이들", "숲", "여섯 번째 미팅",
        "ETA", "아쿠아 맨", "카페수다", "방어 먹으러 가요", "따듯한 사케",
        "포틀럭 파티", "잔잔 파티", "북한산 비봉코스", "현대미술관 야간개장", "ISxP 들의 미팅",
        "빵빵이와 대학생들", "맛집러버들", "느린바이크", "인생 미팅", "빵빵",
        "분좋카, 코노"
    );

    private final TeamUseCase teamUseCase;
    private final TeamMemberUseCase teamMemberUseCase;

    public TeamDataInitializer(TeamUseCase teamUseCase, TeamMemberUseCase teamMemberUseCase) {
        this.teamUseCase = teamUseCase;
        this.teamMemberUseCase = teamMemberUseCase;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        generateDummyData(FEMALE_DUMMY_START_INDEX, FEMALE_DUMMY_END_INDEX);
        generateDummyData(MALE_DUMMY_START_INDEX, MALE_DUMMY_END_INDEX);
    }

    private void generateDummyData(int startIndex, int endIndex) {
        for (long memberId = startIndex; memberId < endIndex + 1; memberId++) {
            TeamSaveCommand teamSaveCommand = TeamSaveCommand.of(memberId, TEAM_NAME.get((int) memberId - 1), (short)2,
                List.of("서울"), List.of(TEAM_NAME.get((int) memberId - 1).split(" ")));
            Long savedId = teamUseCase.saveTeam(teamSaveCommand);
            TeamMemberSaveCommand teamMemberSaveCommand = TeamMemberSaveCommand.of(savedId,
                memberId + 1 > endIndex ? startIndex : memberId + 1);
            teamMemberUseCase.saveTeamMember(teamMemberSaveCommand);
        }
    }
}
