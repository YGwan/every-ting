package com.everyTing.team.domain;

import com.everyTing.team.adapter.out.persistence.entity.TeamDateEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;

@Getter
@JsonInclude(Include.NON_NULL)
public class TeamDates {

    private final List<TeamDate> teamDates;

    private TeamDates(List<TeamDate> teamRequests) {
        this.teamDates = teamRequests;
    }

    public static TeamDates from(List<TeamDateEntity> entities, List<Long> myTeamIds) {
        return new TeamDates(
            entities.stream()
                    .map(entity -> TeamDate.from(entity,
                        myTeamIds.contains(entity.getMenTeamId()) ? entity.getMenTeamId()
                            : entity.getWomenTeamId()))
                    .collect(Collectors.toUnmodifiableList()));
    }
}
