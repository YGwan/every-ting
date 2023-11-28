package com.everyTing.member.utils.cipher;

import com.everyTing.core.exception.TingServerException;
import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static com.everyTing.member.errorCode.MemberServerErrorCode.MSER_001;

@Slf4j
public class PasswordDigest {

    private final MessageDigest messageDigest;

    public PasswordDigest(String algorithm) {
        try {
            this.messageDigest = MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            throw new TingServerException(MSER_001);
        }
    }

    public String encrypt(String password, String salt) {
        return encrypt(password + salt);
    }

    private String encrypt(String plain) {
        messageDigest.update(plain.getBytes());
        return byteToHex(messageDigest.digest());
    }

    private String byteToHex(byte[] bytes) {
        final StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
