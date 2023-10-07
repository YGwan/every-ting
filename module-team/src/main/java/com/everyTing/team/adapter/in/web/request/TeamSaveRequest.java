package com.everyTing.team.adapter.in.web.request;

import java.util.List;
import lombok.Getter;

@Getter
public class TeamSaveRequest {

    private String name;
    private Short memberLimit;
    private String region;
    private List<String> hashtags;

    public TeamSaveRequest() { 
    }

    public TeamSaveRequest(String name, Short memberLimit, String region, List<String> hashtags) {
        this.name = name;
        this.memberLimit = memberLimit;
        this.region = region;
        this.hashtags = hashtags;
    }
}
