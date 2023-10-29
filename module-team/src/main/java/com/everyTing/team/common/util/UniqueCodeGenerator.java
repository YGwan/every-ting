package com.everyTing.team.common.util;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class UniqueCodeGenerator {

    public static String generateCode() {
        StringBuilder builder = new StringBuilder(10);

        String characters = "abcdefghijklmnopqrstuvwxyz0123456789-";

        Set<Character> usedCharacters = new HashSet<>();

        while (builder.length() < 10) {
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
