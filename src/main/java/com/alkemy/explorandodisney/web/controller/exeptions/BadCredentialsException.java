package com.alkemy.explorandodisney.web.controller.exeptions;

import javax.security.sasl.AuthenticationException;

public class BadCredentialsException extends ForbiddenException {
    private static final String DESCRIPTION ="Bad Credential Exception";

    public BadCredentialsException(String message) {
        super(DESCRIPTION+". "+message);
    }
}
