package com.everyTing.team.adapter.out.persistence.entity.data;

import static com.everyTing.team.common.constraints.TeamConstraints.HASHTAG_MAX_LENGTH;
import static com.everyTing.team.common.constraints.TeamConstraints.HASHTAG_MAX_LIMIT;
import static com.everyTing.team.common.constraints.TeamConstraints.HASHTAG_MIN_LENGTH;
import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_004;
import static com.everyTing.team.common.exception.errorCode.TeamErrorCode.TEAM_007;

import com.everyTing.core.exception.TingApplicationException;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Hashtag {

    @Column(name = "content", nullable = false, length = 10)
    private String value;

    protected Hashtag() {
    }

    private Hashtag(String value) {
        this.value = value;
    }

    public static Hashtag from(String value) {
        String trimValue = value.trim();
        validate(trimValue);
        return new Hashtag(trimValue);
    }

    public static List<Hashtag> from(List<String> values) {
        validate(values);

        return values.stream()
                     .map(Hashtag::from)
                     .collect(Collectors.toList());
    }

    public static void validate(String value) {
        if (!(value.length() >= HASHTAG_MIN_LENGTH && value.length() <= HASHTAG_MAX_LENGTH)) {
            throw new TingApplicationException(TEAM_004);
        }
    }

    public static void validate(List<String> values) {
        if (values.size() > HASHTAG_MAX_LIMIT) {
            throw new TingApplicationException(TEAM_007);
        }
    }

    public String getValue() {
        return value;
    }
}
