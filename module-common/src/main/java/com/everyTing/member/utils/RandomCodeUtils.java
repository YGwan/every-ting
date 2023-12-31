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

    public static String getSalt(int n) {
        final var bytes = new byte[n];
        RANDOM.nextBytes(bytes);

        final var sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }

    public static String getSalt() {
        return getSalt(20);
    }
}
