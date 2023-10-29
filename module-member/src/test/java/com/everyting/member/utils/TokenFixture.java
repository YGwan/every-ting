package com.everyting.member.utils;

import com.everyTing.core.token.data.MemberTokens;

public class TokenFixture {

    public static MemberTokens get() {
        return new MemberTokens(
                "accessToken",
                "refreshToken"
        );
    }
}
