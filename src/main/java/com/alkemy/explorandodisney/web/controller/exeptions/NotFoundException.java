package com.alkemy.explorandodisney.web.controller.exeptions;

public class NotFoundException extends RuntimeException{
    private static final String DESCRIPTION ="Not Found Exception (404)";

    public NotFoundException(String message) {
        super(DESCRIPTION+". "+message);
    }
}
