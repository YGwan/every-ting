package com.everyTing.team.common.util;

import static com.everyTing.team.common.util.UniqueStringGenerator.generateUniqueString;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;

class UniqueStringGeneratorTest {

    @DisplayName("unique string generator 테스트 - 10000개 중 겹치는 코드가 있을지")
    @Disabled
    void uniqueTest() {
        List<String> generated = new ArrayList<>();

        for (int i = 0; i < 10000; i++) {
            String newCode = generateUniqueString(10, "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-");
            if (generated.contains(newCode)) {
                System.out.println("i = " + i);
                throw new RuntimeException();
            }
            generated.add(newCode);
        }

        assertThat(generated.size()).isEqualTo(10000);
    }
}