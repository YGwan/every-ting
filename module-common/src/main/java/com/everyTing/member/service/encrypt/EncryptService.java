package com.everyTing.member.service.encrypt;

import com.everyTing.member.domain.data.Password;
import com.everyTing.member.utils.RandomCodeUtils;
import com.everyTing.member.utils.cipher.MemberDataCipher;
import com.everyTing.member.utils.cipher.PasswordDigest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

@Service
public class EncryptService {

    @Value("${password.encryption.algorithm}")
    private String passwordEncryptionAlgorithm;

    private final MemberDataCipher memberDataCipher;
    private final PasswordDigest passwordDigest;

    public EncryptService(
            @Value("${member.encryption.algorithm}")
            String memberDataEncryptionAlgorithm,
            @Value("${member.encryption.key.algorithm}")
            String keyAlgorithm,
            @Value("${member.encryption.key.value}")
            String key) {

        this.memberDataCipher = new MemberDataCipher(
                memberDataEncryptionAlgorithm,
                new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), keyAlgorithm),
                new IvParameterSpec(key.substring(0, 16).getBytes()));

        this.passwordDigest = new PasswordDigest();
    }

    public String encryptedMemberData(String plainText) {
        return memberDataCipher.encrypt(plainText);
    }

    public String decryptedMemberData(String cipherText) {
        return memberDataCipher.decrypt(cipherText);
    }

    public Password encryptedPassword(String password, String salt) {
        final String encryptedPassword = passwordDigest.encrypt(passwordEncryptionAlgorithm, password, salt);
        return new Password(encryptedPassword, salt);
    }

    public String issueSalt() {
        return RandomCodeUtils.getSalt();
    }
}
