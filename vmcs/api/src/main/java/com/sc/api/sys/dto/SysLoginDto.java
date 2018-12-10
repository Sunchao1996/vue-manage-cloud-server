package com.sc.api.sys.dto;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by 孔垂云 on 2017/8/18.
 */
public class SysLoginDto {
    @NotBlank
    private String username;//登录账号
    @NotBlank
    private String password;//登录密码

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
