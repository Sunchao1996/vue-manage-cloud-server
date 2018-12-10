package com.sc.api.site.dto;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;


/**
 * Created by 孔垂云 on 2017/9/2.
 */
public class UserInfoLoginDto {
    @NotBlank
    private String username;//登录账号
    @NotBlank
    @Size(max = 20, min = 6)
    private String password;//登录密码

    @Override
    public String toString() {
        return "UserInfoLoginDto{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public UserInfoLoginDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserInfoLoginDto setPassword(String password) {
        this.password = password;
        return this;
    }
}
