package com.everyTing.team.common.util;

import static org.assertj.core.api.Assertions.assertThat;

import com.everyTing.team.adapter.out.persistence.entity.data.Code;
import com.everyTing.team.adapter.out.persistence.entity.data.Name;
import com.everyTing.team.utils.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

@DisplayName("TeamCodeGenerator test")
@ExtendWith(MockitoExtension.class)
class TeamCodeGeneratorTest extends BaseTest {

    @InjectMocks
    private TeamCodeGenerator sut;

    private String separator = "-";

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(sut, "length", 10);
        ReflectionTestUtils.setField(sut, "randomCharacters", "abcdefghijklmnop");
        ReflectionTestUtils.setField(sut, "separator", separator);
    }

    @DisplayName("team code generator 팀명 공백 제거 확인")
    @Test
    void teamCodeGeneratorTest() {
        Name nameWithSpaces = Name.from("팀 팀 팀");
        Code generated = sut.generate(nameWithSpaces);

        assertThat(generated.getValue()
                            .split(separator)[0]).isEqualTo(nameWithSpaces.getValue()
                                                                          .replace(" ", ""));
    }
}