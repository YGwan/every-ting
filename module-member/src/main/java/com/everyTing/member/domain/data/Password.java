package com.everyTing.member.domain.data;

import com.everyTing.core.exception.TingApplicationException;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.everyTing.member.domain.data.constraints.MemberConstraints.PASSWORD_PATTERN;
import static com.everyTing.member.errorCode.MemberErrorCode.MEMBER_009;

@Getter
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

    public static void validate(String originalPassword) {
        final var password = originalPassword.trim();
        final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        final Matcher matcher = pattern.matcher(password);
        if (!matcher.matches()) {
            throw new TingApplicationException(MEMBER_009);
        }
    }

    public boolean isSame(Password enterPassword) {
        return this.encryptedPassword.equals(enterPassword.encryptedPassword);
    }
}
