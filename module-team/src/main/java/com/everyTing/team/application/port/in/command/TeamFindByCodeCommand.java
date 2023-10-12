package com.everyTing.team.application.port.in.command;

import com.everyTing.team.adapter.out.persistence.entity.data.Code;
import lombok.Getter;

@Getter
public class TeamFindByCodeCommand {

    private final Code code;

    private TeamFindByCodeCommand(Code code) {
        this.code = code;
    }

    public static TeamFindByCodeCommand of(String code) {
        return new TeamFindByCodeCommand(Code.from(code));
    }
}
