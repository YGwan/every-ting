package com.everyTing.core.token.exception;

import com.everyTing.core.exception.TingApplicationException;

public class TokenException extends TingApplicationException {

    public TokenException(Enum<?> errorCode) {
        super(errorCode);
    }
}
