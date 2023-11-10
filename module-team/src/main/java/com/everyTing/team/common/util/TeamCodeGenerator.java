package com.everyTing.team.common.util;

import static com.everyTing.team.common.util.UniqueStringGenerator.generateUniqueString;

import com.everyTing.team.adapter.out.persistence.entity.data.Code;
import com.everyTing.team.adapter.out.persistence.entity.data.Name;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TeamCodeGenerator {

    @Value("${team.code.separator}")
    private String separator;
    @Value("${team.code.random.characters}")
    private String randomCharacters;
    @Value("${team.code.random.string.length}")
    private Integer length;

    public Code generate(Name name) {
        final String nameWithoutSpaces = name.getValue()
                                             .replace(" ", "");

        return Code.from(nameWithoutSpaces + separator + generateUniqueString(length,
            randomCharacters));
    }

}
