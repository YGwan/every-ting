package com.everyTing.team.utils;

import com.everyTing.team.adapter.out.persistence.entity.data.Hashtag;
import java.util.List;
import java.util.stream.Collectors;

public class TeamHashtagEntityFixture {

    public static final List<Hashtag> hashtags =
        List.of("모두E", "보드게임").stream().map(Hashtag::from).collect(Collectors.toList());
}
