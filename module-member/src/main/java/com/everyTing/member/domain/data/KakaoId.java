package com.everyTing.member.domain.data;

import com.everyTing.core.exception.TingApplicationException;
import lombok.Getter;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import static com.everyTing.member.errorCode.MemberErrorCode.MEMBER_005;

@Getter
@Embeddable
public class KakaoId {

    @NotNull
    @Column(name = "kakao_id")
    private String value;

    protected KakaoId() {
    }

    private KakaoId(String value) {
        this.value = value;
    }

    public static KakaoId from(String value) {
        final String trimValue = value.trim();
        validate(trimValue);
        return new KakaoId(trimValue);
    }

    public static void validate(String value) {
        if (!StringUtils.hasText(value)) {
            throw new TingApplicationException(MEMBER_005);
        }
    }
}

