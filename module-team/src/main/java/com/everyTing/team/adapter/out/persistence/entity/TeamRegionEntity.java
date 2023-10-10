package com.everyTing.team.adapter.out.persistence.entity;

import com.everyTing.team.adapter.out.persistence.entity.data.Region;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "team_region")
@Entity
public class TeamRegionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "team_id")
    private TeamEntity team;

    private Region region;

    protected TeamRegionEntity() {
    }

    private TeamRegionEntity(TeamEntity team, Region region) {
        this.team = team;
        this.region = region;
    }

    public static TeamRegionEntity of(TeamEntity team, Region region) {
        return new TeamRegionEntity(team, region);
    }

    public Long getId() {
        return id;
    }

    public String getRegion() {
        return region.getValue();
    }

    public TeamEntity getTeam() {
        return team;
    }
}
