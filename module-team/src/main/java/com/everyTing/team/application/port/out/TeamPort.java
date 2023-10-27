package com.everyTing.team.application.port.out;

import com.everyTing.core.domain.Gender;
import com.everyTing.team.adapter.out.persistence.entity.data.Code;
import com.everyTing.team.adapter.out.persistence.entity.data.Hashtag;
import com.everyTing.team.adapter.out.persistence.entity.data.Major;
import com.everyTing.team.adapter.out.persistence.entity.data.MemberLimit;
import com.everyTing.team.adapter.out.persistence.entity.data.Name;
import com.everyTing.team.adapter.out.persistence.entity.data.Region;
import com.everyTing.team.adapter.out.persistence.entity.data.University;
import com.everyTing.team.domain.Team;
import java.util.List;

public interface TeamPort {

    Team findTeamById(Long teamId);

    Team findTeamByCode(Code code);

    Long saveTeam(Long memberId, Name name, List<Region> regions, University university, Major major,
        Code code, MemberLimit memberLimit, Gender gender, List<Hashtag> hashtags);

    void removeTeam(Long teamId);
}
