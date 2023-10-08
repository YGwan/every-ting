package com.everyTing.team.application.port.out;

import com.everyTing.team.domain.TeamHashtags;

public interface TeamHashtagPort {

    TeamHashtags findHashtags(Long teamId);
}
