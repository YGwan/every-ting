package com.everyTing.team.common.constraints;

import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TeamConstraints {

    public static int NAME_MIN_LENGTH;
    public static int NAME_MAX_LENGTH;

    public static int MEMBER_MIN_LIMIT;
    public static int MEMBER_MAX_LIMIT;

    public static int HASHTAG_MIN_LENGTH;
    public static int HASHTAG_MAX_LENGTH;

    public static List<String> REGIONS;

    public TeamConstraints(
        @Value("${name.length.min}")
        int nameMinLength,
        @Value("${name.length.max}")
        int nameMaxLength,
        @Value("${member.limit.min}")
        int memberMinLimit,
        @Value("${member.limit.max}")
        int memberMaxLimit,
        @Value("${hashtag.content.length.min}")
        int hashtagMinLength,
        @Value("${hashtag.content.length.max}")
        int hashtagMaxLength,
        @Value("${regions}")
        List<String> regions
    ) {
        NAME_MIN_LENGTH = nameMinLength;
        NAME_MAX_LENGTH = nameMaxLength;
        MEMBER_MIN_LIMIT = memberMinLimit;
        MEMBER_MAX_LIMIT = memberMaxLimit;
        HASHTAG_MIN_LENGTH = hashtagMinLength;
        HASHTAG_MAX_LENGTH = hashtagMaxLength;
        REGIONS = regions;
    }
}
