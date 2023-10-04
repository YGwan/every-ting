package com.everyTing.core.resolver;

import lombok.Getter;

@Getter
public class LoginMemberInfo {

    private final Long id;

    public LoginMemberInfo(Long id) {
        this.id = id;
    }
}
