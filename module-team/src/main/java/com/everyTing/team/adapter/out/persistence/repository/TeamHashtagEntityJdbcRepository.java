package com.everyTing.team.adapter.out.persistence.repository;

import static java.time.LocalDateTime.now;

import com.everyTing.team.adapter.out.persistence.entity.TeamHashtagEntity;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TeamHashtagEntityJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    public TeamHashtagEntityJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveAll(List<TeamHashtagEntity> hashtags) {
        String sql = "insert into team_hashtag (content, team_id, created_at) values (?, ?, ?)";

        jdbcTemplate.batchUpdate(sql,
            hashtags,
            hashtags.size(),
            (PreparedStatement ps, TeamHashtagEntity hashtag) -> {
                ps.setString(1, hashtag.getContent());
                ps.setLong(2, hashtag.getTeam().getId());
                ps.setTimestamp(3, Timestamp.valueOf(now()));
            }
        );
    }
}
