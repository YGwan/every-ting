package com.everyTing.team.adapter.out.persistence.entity;

import com.everyTing.core.domain.AuditingFields;
import com.everyTing.team.adapter.out.persistence.entity.data.Role;
import com.sun.istack.NotNull;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Table(name = "team_member",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"teamId", "memberId"})
    })
@Entity
public class TeamMemberEntity extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long teamId;

    @NotNull
    private Long memberId;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "fromTeamMember", cascade = CascadeType.REMOVE)
    List<TeamLikeEntity> likes = new ArrayList<>();

    protected TeamMemberEntity() {
    }

    private TeamMemberEntity(Long teamId, Long memberId, Role role) {
        this.teamId = teamId;
        this.memberId = memberId;
        this.role = role;
    }

    public static TeamMemberEntity of(Long teamId, Long memberId, Role role) {
        return new TeamMemberEntity(teamId, memberId, role);
    }

    public Long getId() {
        return id;
    }

    public Long getTeamId() {
        return teamId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public Role getRole() {
        return role;
    }
}
