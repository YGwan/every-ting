package com.everyTing.member.domain.data;

import com.everyTing.core.exception.TingApplicationException;
import com.everyTing.member.utils.PasswordEncoder;
import com.everyTing.member.utils.RandomCodeUtils;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.everyTing.member.domain.data.constraints.MemberConstraints.PASSWORD_PATTERN;
import static com.everyTing.member.errorCode.MemberErrorCode.MEMBER_009;

@Embeddable
public class Password {

    @Column(name = "password")
    @NotNull
    private String encryptedPassword;

    @NotNull
    private String salt;

    protected Password() {
    }

    public Password(String encryptedPassword, String salt) {
        this.encryptedPassword = encryptedPassword;
        this.salt = salt;
    }

    public static Password encryptedPassword(String originalPassword) {
        final String passwordTrim = originalPassword.trim();
        validate(passwordTrim);

        final String salt = RandomCodeUtils.getSalt();
        final String encryptedPassword = PasswordEncoder.passwordEncoder(originalPassword, salt);
        return new Password(encryptedPassword, salt);
    }

    public static void validate(String originalPassword) {
        final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        final Matcher matcher = pattern.matcher(originalPassword);
        if (!matcher.matches()) {
            throw new TingApplicationException(MEMBER_009);
        }
    }

    public boolean isSame(String enterPassword) {
        final String EncryptedEnterPassword = PasswordEncoder.passwordEncoder(enterPassword, salt);
        return encryptedPassword.equals(EncryptedEnterPassword);
    }
}
