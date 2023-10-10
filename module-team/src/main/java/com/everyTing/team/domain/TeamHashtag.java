package com.everyTing.team.domain;

import com.everyTing.team.adapter.out.persistence.entity.TeamHashtagEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;

@Getter
@JsonInclude(Include.NON_NULL)
public class TeamHashtag {

    private final Long id;
    private final String content;

    private TeamHashtag(Long id, String content) {
        this.id = id;
        this.content = content;
    }

    public static TeamHashtag from(TeamHashtagEntity entity) {
        return new TeamHashtag(entity.getId(), entity.getContent());
    }
}
