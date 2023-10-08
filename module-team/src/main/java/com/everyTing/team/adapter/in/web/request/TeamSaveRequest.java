package com.everyTing.team.adapter.in.web.request;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Getter;

@Getter
public class TeamSaveRequest {

    @Schema(description = "팀명, 3글자 이상 20글자 이하")
    private String name;

    @Schema(description = "팀명 제한 수, 2명이상 6명 이하", defaultValue = "3")
    private Short memberLimit;

    @Schema(description = "지역", defaultValue = "서울 남부")
    private String region;

    @Schema(description = "해시태그, 1글자이상 10글자 이하", nullable = true)
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
