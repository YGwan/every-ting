package com.everyTing.team.domain;

import com.everyTing.team.adapter.out.persistence.entity.TeamHashtagEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;

@Getter
@JsonInclude(Include.NON_NULL)
public class TeamHashtags {

    private final List<TeamHashtag> hashtags;

    private TeamHashtags(List<TeamHashtag> hashtags) {
        this.hashtags = hashtags;
    }

    public static TeamHashtags from(List<TeamHashtagEntity> entities) {
        return new TeamHashtags(entities.stream()
                                        .map(TeamHashtag::from)
                                        .collect(Collectors.toUnmodifiableList()));
    }
}
