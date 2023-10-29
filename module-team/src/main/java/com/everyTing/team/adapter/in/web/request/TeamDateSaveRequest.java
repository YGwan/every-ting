package com.everyTing.team.adapter.in.web.request;

import lombok.Getter;

@Getter
public class TeamDateSaveRequest {

    private Long requestId;

    public TeamDateSaveRequest() {
    }

    public TeamDateSaveRequest(Long requestId) {
        this.requestId = requestId;
    }
}
