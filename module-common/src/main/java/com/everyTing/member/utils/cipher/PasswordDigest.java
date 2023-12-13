package com.everyTing.member.utils.cipher;

import com.everyTing.core.exception.TingServerException;
import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static com.everyTing.member.errorCode.MemberServerErrorCode.MSER_001;

@Slf4j
public class PasswordDigest {

    public String encrypt(String algorithm, String password, String salt) {
        return encrypt(algorithm, password + salt);
    }

    private String encrypt(String algorithm, String plain) {
        try {
            final var messageDigest = MessageDigest.getInstance(algorithm);
            messageDigest.update(plain.getBytes());
            return byteToHex(messageDigest.digest());

        } catch (NoSuchAlgorithmException e) {
            throw new TingServerException(MSER_001);
        }
    }

    private String byteToHex(byte[] bytes) {
        final var hexSb = new StringBuilder();
        for (byte b : bytes) {
            hexSb.append(String.format("%02x", b));
        }
        return hexSb.toString();
    }
}
