package com.everyTing.team.common.util;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class UniqueStringGenerator {

    public static String generateUniqueString(int length, String characters) {
        StringBuilder builder = new StringBuilder(length);
        Set<Integer> usedIndexes = new HashSet<>();

        while (builder.length() < length) {
            int randomIndex = ThreadLocalRandom.current().nextInt(characters.length());

            if (usedIndexes.add(randomIndex)) {
                builder.append(characters.charAt(randomIndex));
            }
        }

        return builder.toString();
    }
}
