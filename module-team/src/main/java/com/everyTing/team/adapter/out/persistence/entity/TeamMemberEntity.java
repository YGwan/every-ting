package com.everyTing.team.adapter.out.persistence.entity;

import com.everyTing.core.domain.AuditingFields;
import com.everyTing.team.adapter.out.persistence.entity.constant.Role;
import com.sun.istack.NotNull;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Table(name = "team_member",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"team_id", "member_id"})
    })
@Entity
public class TeamMemberEntity extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "team_id")
    private TeamEntity team;

    @NotNull
    @Column(name = "member_id")
    private Long memberId;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;

    protected TeamMemberEntity() {
    }

    private TeamMemberEntity(TeamEntity team, Long memberId, Role role) {
        this.team = team;
        this.memberId = memberId;
        this.role = role;
    }
}
