package com.everyTing.team.adapter.out.redis.cache;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class TeamDateCountCache {

    private Long teamId;
    private Integer count;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime createdTime;

    protected TeamDateCountCache() {
    }

    private TeamDateCountCache(Long teamId, Integer count, LocalDateTime createdTime) {
        this.teamId = teamId;
        this.count = count;
        this.createdTime = createdTime;
    }

    public static TeamDateCountCache from(Long teamId) {
        return new TeamDateCountCache(teamId, 0, LocalDateTime.now());
    }

    public void increaseCount() {
        count++;
    }

    @Override
    public String toString() {
        return "TeamDateCountCache{" +
            "teamId=" + teamId +
            ", count=" + count +
            ", createdTime=" + createdTime +
            '}';
    }
}
