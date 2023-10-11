package com.everyTing.team.application.port.in.command;

import com.everyTing.team.adapter.out.persistence.entity.data.Hashtag;
import com.everyTing.team.adapter.out.persistence.entity.data.MemberLimit;
import com.everyTing.team.adapter.out.persistence.entity.data.Name;
import com.everyTing.team.adapter.out.persistence.entity.data.Region;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;

@Getter
public class TeamSaveCommand {

    private final Long memberId;
    private final Name name;
    private final MemberLimit memberLimit;
    private final List<Region> regions;
    private final List<Hashtag> hashtags;

    private TeamSaveCommand(Long memberId, Name name, MemberLimit memberLimit, List<Region> regions,
        List<Hashtag> hashtags) {
        this.memberId = memberId;
        this.name = name;
        this.memberLimit = memberLimit;
        this.regions = regions;
        this.hashtags = hashtags;
    }

    public static TeamSaveCommand of(Long memberId, String name, Short memberLimit,
        List<String> regions,
        List<String> hashtags) {
        return new TeamSaveCommand(memberId, Name.from(name), MemberLimit.from(memberLimit),
            regions.stream()
                   .map(Region::from)
                   .collect(Collectors.toList()),
            hashtags.stream()
                    .map(Hashtag::from)
                    .collect(Collectors.toList()));
    }
}
