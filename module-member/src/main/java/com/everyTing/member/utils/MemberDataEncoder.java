package com.everyTing.member.utils;

import com.everyTing.core.exception.TingServerException;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import static com.everyTing.member.errorCode.MemberServerErrorCode.MSER_002;
import static com.everyTing.member.errorCode.MemberServerErrorCode.MSER_003;
import static com.everyTing.member.utils.secureBundle.SecureBundle.*;

@Slf4j
public class MemberDataEncoder {

    private static final String ALGORITHM = MEMBER_ENCRYPTION_ALGORITHM;
    private static final String PRIVATE_KEY_ALGORITHM = MEMBER_ENCRYPTION_KEY_ALGORITHM;
    private static final String PRIVATE_KEY = MEMBER_ENCRYPTION_KEY;

    public static String encrypt(String plainText) {
        byte[] encryptBytes = encrypt(plainText.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptBytes);
    }

    private static byte[] encrypt(byte[] plainBytes) {
        try {
            final Cipher cipher = Cipher.getInstance(ALGORITHM);
            final SecretKeySpec keySpec = new SecretKeySpec(PRIVATE_KEY.getBytes(StandardCharsets.UTF_8), PRIVATE_KEY_ALGORITHM);
            final IvParameterSpec ivParamSpec = new IvParameterSpec(PRIVATE_KEY.substring(0, 16).getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParamSpec);
            return cipher.doFinal(plainBytes);

        } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidAlgorithmParameterException |
                 InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            log.error(e.getMessage());
            throw new TingServerException(MSER_002);
        }
    }

    public static String decrypt(String encryptText) {
        final var plain = decrypt((Base64.getDecoder().decode(encryptText)));
        return new String(plain);
    }

    private static byte[] decrypt(byte[] encryptBytes) {
        try {
            final Cipher cipher = Cipher.getInstance(ALGORITHM);
            final SecretKeySpec keySpec = new SecretKeySpec(PRIVATE_KEY.getBytes(StandardCharsets.UTF_8), PRIVATE_KEY_ALGORITHM);
            final IvParameterSpec ivParamSpec = new IvParameterSpec(PRIVATE_KEY.substring(0, 16).getBytes());
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParamSpec);
            return cipher.doFinal(encryptBytes);

        } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidAlgorithmParameterException |
                 InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            log.error(e.getMessage());
            throw new TingServerException(MSER_003);
        }
    }
}
