package com.alkemy.explorandodisney.web.controller.exeptions;

public class CustomErrorType {
    private String errorMessage;

    public CustomErrorType(String errorMessage) {
        super();
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
