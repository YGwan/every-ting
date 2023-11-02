package com.everyTing.team.adapter.in.web.request;

import java.util.List;
import lombok.Getter;

@Getter
public class TeamHashtagModifyRequest {

    private List<Long> removedHashtagIds;
    private List<String> newHashtags;

    public TeamHashtagModifyRequest() {
    }

    public TeamHashtagModifyRequest(List<Long> removedHashtagIds, List<String> newHashtags) {
        this.removedHashtagIds = removedHashtagIds;
        this.newHashtags = newHashtags;
    }
}
