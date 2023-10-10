package com.everyTing.member.utils;

import java.security.SecureRandom;
import java.util.Random;

public class RandomCodeUtils {

    private static final Random RANDOM = new SecureRandom();

    public static String generate() {
        final StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 6; i ++) {
            sb.append(RANDOM.nextInt(10));
        }
        return sb.toString();
    }
}
