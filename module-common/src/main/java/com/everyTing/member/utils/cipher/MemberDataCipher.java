package com.everyTing.member.utils.cipher;

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

@Slf4j
public class MemberDataCipher {

    private final String memberDataEncryptionAlgorithm;
    private final SecretKeySpec keySpec;
    private final IvParameterSpec ivParamSpec;

    public MemberDataCipher(String memberDataEncryptionAlgorithm, SecretKeySpec keySpec, IvParameterSpec ivParamSpec) {
        this.memberDataEncryptionAlgorithm = memberDataEncryptionAlgorithm;
        this.keySpec = keySpec;
        this.ivParamSpec = ivParamSpec;
    }

    public String encrypt(String plainText) {
        byte[] encryptBytes = encrypt(plainText.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptBytes);
    }

    private byte[] encrypt(byte[] plainBytes) {
        try {
            final Cipher cipher = Cipher.getInstance(memberDataEncryptionAlgorithm);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParamSpec);
            return cipher.doFinal(plainBytes);

        } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidAlgorithmParameterException |
                 InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            log.error(e.getMessage());
            throw new TingServerException(MSER_002);
        }
    }

    public String decrypt(String encryptText) {
        final var plain = decrypt((Base64.getDecoder().decode(encryptText)));
        return new String(plain);
    }

    private byte[] decrypt(byte[] encryptBytes) {
        try {
            final Cipher cipher = Cipher.getInstance(memberDataEncryptionAlgorithm);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParamSpec);
            return cipher.doFinal(encryptBytes);

        } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidAlgorithmParameterException |
                 InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            log.error(e.getMessage());
            throw new TingServerException(MSER_003);
        }
    }
}
