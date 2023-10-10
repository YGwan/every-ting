package com.everyTing.team.adapter.out.persistence.repository;

import com.everyTing.team.adapter.out.persistence.entity.TeamRegionEntity;
import java.sql.PreparedStatement;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TeamRegionEntityJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    public TeamRegionEntityJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveAll(List<TeamRegionEntity> regions) {
        String sql = "insert into team_region (region, team_id) values (?, ?)";

        jdbcTemplate.batchUpdate(sql,
            regions,
            regions.size(),
            (PreparedStatement ps, TeamRegionEntity region) -> {
                ps.setString(1, region.getRegion());
                ps.setLong(2, region.getTeam().getId());
            }
        );
    }
}
