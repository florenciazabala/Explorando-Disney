package com.alkemy.explorandodisney.domain;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Blob;

public class UserVO {

    @Email(message = "Incorrect format mail")
    @NotEmpty(message = "Mail cant't be empty")
    private String mail;
    @NotEmpty(message = "Username cant't be empty")
    private String username;
    @NotEmpty(message = "Password cant't be empty")
    @Pattern(regexp = "^(?=.*\\d).{4}$", flags = Pattern.Flag.UNICODE_CASE, message = "The password must contain at least 4 characters and at least one number")
    //@Size(min = 4, message = "The password must contain at least 4 characters")

    private String password;

    public UserVO() {
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
