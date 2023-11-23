package com.everyTing.member.utils;

import com.everyTing.core.exception.TingServerException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static com.everyTing.member.errorCode.MemberServerErrorCode.MSER_001;
import static com.everyTing.member.utils.secureBundle.SecureBundle.PASSWORD_ENCRYPTION_ALGORITHM;

public class PasswordEncoder {

    private static final String ALGORITHM = PASSWORD_ENCRYPTION_ALGORITHM;

    public static String passwordEncoder(String originalPassword, String salt) {
        return encrypt(originalPassword + salt);
    }

    private static String encrypt(String text) {
        try {
            final MessageDigest md = MessageDigest.getInstance(ALGORITHM);
            md.update(text.getBytes());
            return byteToHex(md.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new TingServerException(MSER_001);
        }
    }

    private static String byteToHex(byte[] bytes) {
        final StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
