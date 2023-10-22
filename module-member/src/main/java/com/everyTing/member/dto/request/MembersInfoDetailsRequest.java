package com.everyTing.member.dto.request;

import lombok.Getter;

import java.util.List;

@Getter
public class MembersInfoDetailsRequest {

    private List<Long> memberIds;

    public MembersInfoDetailsRequest() {
    }

    public MembersInfoDetailsRequest(List<Long> memberIds) {
        this.memberIds = memberIds;
    }
}
