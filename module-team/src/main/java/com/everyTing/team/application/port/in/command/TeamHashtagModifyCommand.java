package com.everyTing.team.application.port.in.command;

import com.everyTing.team.adapter.out.persistence.entity.data.Hashtag;
import java.util.List;
import lombok.Getter;

@Getter
public class TeamHashtagModifyCommand {

    private final Long teamId;
    private final List<Long> removedHashtagIds;
    private final List<Hashtag> newHashtags;
    private final Long memberId;

    public TeamHashtagModifyCommand(Long teamId, List<Long> removedHashtagIds,
        List<Hashtag> newHashtags, Long memberId) {
        this.teamId = teamId;
        this.removedHashtagIds = removedHashtagIds;
        this.newHashtags = newHashtags;
        this.memberId = memberId;
    }

    public static TeamHashtagModifyCommand of(Long teamId, List<Long> removedHashtagIds, List<String> newHashtags,
        Long memberId) {
        return new TeamHashtagModifyCommand(
            teamId,
            removedHashtagIds,
            Hashtag.from(newHashtags),
            memberId
        );
    }
}
