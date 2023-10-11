package com.everyTing.team.utils;

import com.everyTing.team.adapter.out.persistence.entity.TeamRegionEntity;
import com.everyTing.team.adapter.out.persistence.entity.data.Region;
import java.util.List;
import java.util.stream.Collectors;

public class TeamRegionEntityFixture {

    public static final List<Region> regions = List.of(Region.from("서울"), Region.from("인천"));

    public static List<TeamRegionEntity> get() {
        return regions.stream()
                      .map(region -> TeamRegionEntity.of(TeamEntityFixture.get(1L), region))
                      .collect(Collectors.toUnmodifiableList());
    }
}
