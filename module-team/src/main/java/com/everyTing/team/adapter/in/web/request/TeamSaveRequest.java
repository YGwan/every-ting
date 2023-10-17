package com.everyTing.team.adapter.in.web.request;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Getter;

@Getter
public class TeamSaveRequest {

    @Schema(description = "팀명, 3글자 이상 20글자 이하")
    private String name;

    @Schema(description = "팀명 제한 수, 2명 이상 6명 이하", defaultValue = "3")
    private Short memberLimit;

    @Schema(description = "지역", allowableValues = {"서울", "인천"})
    private List<String> regions;

    @Schema(description = "해시태그, 1글자 이상 7글자 이하, 7개 이하", nullable = true)
    private List<String> hashtags;

    public TeamSaveRequest() {
    }

    public TeamSaveRequest(String name, Short memberLimit, List<String> regions,
        List<String> hashtags) {
        this.name = name;
        this.memberLimit = memberLimit;
        this.regions = regions;
        this.hashtags = hashtags;
    }
}
