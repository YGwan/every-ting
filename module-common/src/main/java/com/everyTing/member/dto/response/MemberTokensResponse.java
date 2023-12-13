package com.everyTing.member.dto.response;

import com.everyTing.core.token.data.MemberTokens;
import lombok.Getter;

@Getter
public class MemberTokensResponse {

    private Long memberId;

    private MemberTokens memberTokens;

    public MemberTokensResponse() {
    }

    public MemberTokensResponse(Long memberId, MemberTokens memberTokens) {
        this.memberId = memberId;
        this.memberTokens = memberTokens;
    }
}
