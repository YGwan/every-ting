package com.everyTing.team.common.util;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class UniqueStringGenerator {

    public static String generateUniqueString(int length, String characters) {
        StringBuilder builder = new StringBuilder(10);

        Set<Character> usedCharacters = new HashSet<>();

        while (builder.length() < length) {
            int index = ThreadLocalRandom.current().nextInt(characters.length());
            char c = characters.charAt(index);

            if (!usedCharacters.contains(c)) {
                usedCharacters.add(c);
                builder.append(c);
            }
        }

        return builder.toString();
    }
}
