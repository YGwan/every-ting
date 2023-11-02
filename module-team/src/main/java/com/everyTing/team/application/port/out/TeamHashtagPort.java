package com.everyTing.team.application.port.out;

import com.everyTing.team.adapter.out.persistence.entity.data.Hashtag;
import com.everyTing.team.domain.TeamHashtags;
import java.util.List;

public interface TeamHashtagPort {

    TeamHashtags findHashtags(Long teamId);

    void removeHashtags(List<Long> hashtagIds);

    void saveHashtags(Long teamId, List<Hashtag> hashtags);
}
