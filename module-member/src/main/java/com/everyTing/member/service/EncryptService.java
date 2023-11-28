package com.everyTing.member.service;

import com.everyTing.member.utils.cipher.MemberDataCipher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

@Service
public class EncryptService {

    private final MemberDataCipher memberDataCipher;

    public EncryptService(
            @Value("${password.encryption.algorithm}")
            String passwordEncryptionAlgorithm,
            @Value("${default.salt.length}")
            int defaultSaltLength,
            @Value("${member.encryption.algorithm}")
            String memberDataEncryptionAlgorithm,
            @Value("${member.encryption.key.algorithm}")
            String keyAlgorithm,
            @Value("${member.encryption.key.value}")
            String key) {
        this.memberDataCipher = new MemberDataCipher(
                memberDataEncryptionAlgorithm,
                new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), keyAlgorithm),
                new IvParameterSpec(key.substring(0, 16).getBytes())
        );
    }

    public String encryptedMemberData(String plainText) {
        return memberDataCipher.encrypt(plainText);
    }

    public String decryptedMemberData(String cipherText) {
        return memberDataCipher.decrypt(cipherText);
    }
}
