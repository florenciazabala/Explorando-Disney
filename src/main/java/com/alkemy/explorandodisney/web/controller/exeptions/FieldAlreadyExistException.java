package com.alkemy.explorandodisney.web.controller.exeptions;

public class FieldAlreadyExistException extends ConflictException{
    private static final String DESCRIPTION ="Field Already Exist Exception ";

    public FieldAlreadyExistException(String message) {
        super(DESCRIPTION+". "+message);
    }
}
