package com.sc.sys.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

/**
 * what:  系统用户
 *
 * @author 孙超 created on 2018/11/8
 */
public class SysUser {
    private Integer id;
    private String userName;
    private String userMobile;
    private String userRealName;
    @JsonIgnore
    private String userToken;
    private Date userLastLoginTime;
    private String userAvatar;
    private String userIntroduction;
    private Integer userStatus;
    private String roles;
    @JsonIgnore
    private String userPassword;
    @JsonIgnore
    private String randomCode;
    @JsonIgnore
    private String userIp;
    private Boolean userStatusB;

    public Boolean getUserStatusB() {
        if(this.userStatus == 0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public String toString() {
        return "SysUser{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userMobile='" + userMobile + '\'' +
                ", userRealName='" + userRealName + '\'' +
                ", userToken='" + userToken + '\'' +
                ", userLastLoginTime=" + userLastLoginTime +
                ", userAvatar='" + userAvatar + '\'' +
                ", userIntroduction='" + userIntroduction + '\'' +
                ", userStatus=" + userStatus +
                ", roles='" + roles + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", randomCode='" + randomCode + '\'' +
                ", userIp='" + userIp + '\'' +
                '}';
    }

    public String getUserIp() {
        return userIp;
    }

    public SysUser setUserIp(String userIp) {
        this.userIp = userIp;
        return this;
    }

    public String getRandomCode() {
        return randomCode;
    }

    public SysUser setRandomCode(String randomCode) {
        this.randomCode = randomCode;
        return this;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public SysUser setUserPassword(String userPassword) {
        this.userPassword = userPassword;
        return this;
    }

    public String getRoles() {
        return roles;
    }

    public SysUser setRoles(String roles) {
        this.roles = roles;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public SysUser setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public SysUser setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public SysUser setUserMobile(String userMobile) {
        this.userMobile = userMobile;
        return this;
    }

    public String getUserRealName() {
        return userRealName;
    }

    public SysUser setUserRealName(String userRealName) {
        this.userRealName = userRealName;
        return this;
    }

    public String getUserToken() {
        return userToken;
    }

    public SysUser setUserToken(String userToken) {
        this.userToken = userToken;
        return this;
    }

    public Date getUserLastLoginTime() {
        return userLastLoginTime;
    }

    public SysUser setUserLastLoginTime(Date userLastLoginTime) {
        this.userLastLoginTime = userLastLoginTime;
        return this;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public SysUser setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
        return this;
    }

    public String getUserIntroduction() {
        return userIntroduction;
    }

    public SysUser setUserIntroduction(String userIntroduction) {
        this.userIntroduction = userIntroduction;
        return this;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public SysUser setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
        return this;
    }
}
