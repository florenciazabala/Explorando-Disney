package com.alkemy.explorandodisney.web.controller.exeptions;

public class ErrorMessage {
    private String exeption;
    private String message;
    private String path;

    public ErrorMessage(Exception exeption, String path) {
        this.exeption = exeption.getClass().getSimpleName();
        this.message =  exeption.getMessage();
        this.path = path;
    }

    public String getExeption() {
        return exeption;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }

    @Override
    public String toString() {
        return "ErrorMessage{" +
                "exeption='" + exeption + '\'' +
                ", message='" + message + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
