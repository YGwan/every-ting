package com.everyTing.member.utils;

import java.security.SecureRandom;
import java.util.Random;

public class RandomCodeUtils {

    private static final Random RANDOM = new SecureRandom();

    public static String generate() {
        byte[] randomCode = new byte[6];
        RANDOM.nextBytes(randomCode);
        final StringBuilder sb = new StringBuilder();
        for (byte b : randomCode) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
