package com.everyTing.team.utils;

import com.everyTing.team.common.constraints.TeamConstraints;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.util.ReflectionTestUtils;

public abstract class BaseTest {

    @BeforeAll
    public static void setUpConstraintEnv() {
        ReflectionTestUtils.setField(TeamConstraints.class, "NAME_MIN_LENGTH", 3);
        ReflectionTestUtils.setField(TeamConstraints.class, "NAME_MAX_LENGTH", 20);
        ReflectionTestUtils.setField(TeamConstraints.class, "MEMBER_MIN_LIMIT", 2);
        ReflectionTestUtils.setField(TeamConstraints.class, "MEMBER_MAX_LIMIT", 6);
        ReflectionTestUtils.setField(TeamConstraints.class, "HASHTAG_MAX_LIMIT", 7);
        ReflectionTestUtils.setField(TeamConstraints.class, "HASHTAG_MIN_LENGTH", 1);
        ReflectionTestUtils.setField(TeamConstraints.class, "HASHTAG_MAX_LENGTH", 10);
        ReflectionTestUtils.setField(TeamConstraints.class, "DAILY_REQUEST_LIMIT", 2);
        ReflectionTestUtils.setField(TeamConstraints.class, "REGIONS",
            List.of("서울", "인천", "경기 남부", "경기 북부", "강원", "충북", "충남", "대전", "세종", "경북",
                "경남", "부산", "대구", "울산", "전남", "광주", "전북", "제주도"));
    }
}
