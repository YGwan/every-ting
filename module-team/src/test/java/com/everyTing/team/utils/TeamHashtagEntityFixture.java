package com.everyTing.team.utils;

import com.everyTing.team.adapter.out.persistence.entity.TeamHashtagEntity;
import com.everyTing.team.adapter.out.persistence.entity.data.Hashtag;
import java.util.List;
import java.util.stream.Collectors;

public class TeamHashtagEntityFixture {

    public static final List<Hashtag> hashtags =
        List.of("모두E", "보드게임")
            .stream()
            .map(Hashtag::from)
            .collect(Collectors.toList());

    public static List<TeamHashtagEntity> get() {
        return hashtags.stream()
                       .map(hashtag -> TeamHashtagEntity.of(TeamEntityFixture.get(1L), hashtag))
                       .collect(Collectors.toUnmodifiableList());
    }
}
