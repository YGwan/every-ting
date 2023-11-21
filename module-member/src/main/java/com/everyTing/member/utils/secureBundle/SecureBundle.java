package com.everyTing.member.utils.secureBundle;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SecureBundle {

    public static String PASSWORD_ENCRYPTION_ALGORITHM;
    public static int DEFAULT_SALT_LENGTH;

    public SecureBundle(
            @Value("${member.encryption.password.algorithm}")
            String passwordEncryptionAlgorithm,
            @Value("${member.encryption.password.salt.length}")
            int defaultSaltLength
    ) {
        PASSWORD_ENCRYPTION_ALGORITHM = passwordEncryptionAlgorithm;
        DEFAULT_SALT_LENGTH = defaultSaltLength;
    }
}
