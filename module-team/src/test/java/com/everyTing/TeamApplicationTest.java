package com.everyTing;

import com.everyTing.team.common.initializer.TeamDataInitializer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class TeamApplicationTest {

    @MockBean
    private TeamDataInitializer teamDataInitializer;

    @Test
    void contextLoads() {
    }
}