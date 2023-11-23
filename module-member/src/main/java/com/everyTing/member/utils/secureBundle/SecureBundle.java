package com.everyTing.member.utils.secureBundle;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SecureBundle {

    public static String PASSWORD_ENCRYPTION_ALGORITHM;
    public static int DEFAULT_SALT_LENGTH;
    public static String MEMBER_ENCRYPTION_ALGORITHM;
    public static String MEMBER_ENCRYPTION_KEY_ALGORITHM;
    public static String MEMBER_ENCRYPTION_KEY;

    public SecureBundle(
            @Value("${password.encryption.algorithm}")
            String passwordEncryptionAlgorithm,
            @Value("${default.salt.length}")
            int defaultSaltLength,
            @Value("${member.encryption.algorithm}")
            String memberDataEncryptionAlgorithm,
            @Value("${member.encryption.key.algorithm}")
            String memberEncryptionKeyAlgorithm,
            @Value("${member.encryption.key.value}")
            String memberEncryptionKey
    ) {
        PASSWORD_ENCRYPTION_ALGORITHM = passwordEncryptionAlgorithm;
        DEFAULT_SALT_LENGTH = defaultSaltLength;
        MEMBER_ENCRYPTION_ALGORITHM = memberDataEncryptionAlgorithm;
        MEMBER_ENCRYPTION_KEY = memberEncryptionKey;
        MEMBER_ENCRYPTION_KEY_ALGORITHM = memberEncryptionKeyAlgorithm;
    }
}
