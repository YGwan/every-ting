package com.everyTing.team.adapter.out.persistence.entity;

import com.everyTing.core.domain.CreatedDateFields;
import com.everyTing.team.adapter.out.persistence.entity.data.Hashtag;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "team_hashtag")
@Entity
public class TeamHashtagEntity extends CreatedDateFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "team_id")
    private TeamEntity team;

    private Hashtag content;

    protected TeamHashtagEntity() {
    }

    private TeamHashtagEntity(TeamEntity team, Hashtag content) {
        this.team = team;
        this.content = content;
    }

    public static TeamHashtagEntity of(TeamEntity team, Hashtag content) {
        return new TeamHashtagEntity(team, content);
    }

    public Long getId() {
        return id;
    }

    public TeamEntity getTeam() {
        return team;
    }

    public String getContent() {
        return content.getValue();
    }
}
