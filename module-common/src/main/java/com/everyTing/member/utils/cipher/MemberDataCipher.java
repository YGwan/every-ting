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

import static com.everyTing.member.errorCode.MemberServerErrorCode.*;

@Slf4j
public class MemberDataCipher {

    private final Cipher encryptCipher;
    private final Cipher decryptCipher;

    public MemberDataCipher(String algorithm, SecretKeySpec keySec, IvParameterSpec ivParamSpec) {
        try {
            this.encryptCipher = Cipher.getInstance(algorithm);
            this.encryptCipher.init(Cipher.ENCRYPT_MODE, keySec, ivParamSpec);

            this.decryptCipher = Cipher.getInstance(algorithm);
            this.decryptCipher.init(Cipher.DECRYPT_MODE, keySec, ivParamSpec);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException |
                 InvalidKeyException e) {
            log.error(e.getMessage());
            throw new TingServerException(MSER_002);
        }
    }

    public String encrypt(String plain) {
        final var encrypted = encrypt(plain.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encrypted);
    }

    public byte[] encrypt(byte[] plain) {
        try {
            return encryptCipher.doFinal(plain);
        } catch (BadPaddingException | IllegalBlockSizeException e) {
            throw new TingServerException(MSER_003);
        }
    }

    public String decrypt(String encrypted) {
        final var plain = decrypt((Base64.getDecoder().decode(encrypted)));
        return new String(plain);
    }

    public byte[] decrypt(byte[] encrypted) {
        try {
            return decryptCipher.doFinal(encrypted);
        } catch (BadPaddingException | IllegalBlockSizeException e) {
            log.error(e.getMessage());
            throw new TingServerException(MSER_004);
        }
    }
}
