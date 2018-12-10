package com.sc.sys.model;

/**
 * what:    修改密码信息接收
 *
 * @author 孙超 created on 2018/11/28
 */
public class SysPwd {
    private String userName;//用户名
    private String userPwd;//用户密码
    private String newUserPwd;//用户新密码
    private String sureNewUserPwd;//用户确认新密码

    @Override
    public String toString() {
        return "SysPwd{" +
                "userName='" + userName + '\'' +
                ", userPwd='" + userPwd + '\'' +
                ", newUserPwd='" + newUserPwd + '\'' +
                ", sureNewUserPwd='" + sureNewUserPwd + '\'' +
                '}';
    }

    public String getUserName() {
        return userName;
    }

    public SysPwd setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public SysPwd setUserPwd(String userPwd) {
        this.userPwd = userPwd;
        return this;
    }

    public String getNewUserPwd() {
        return newUserPwd;
    }

    public SysPwd setNewUserPwd(String newUserPwd) {
        this.newUserPwd = newUserPwd;
        return this;
    }

    public String getSureNewUserPwd() {
        return sureNewUserPwd;
    }

    public SysPwd setSureNewUserPwd(String sureNewUserPwd) {
        this.sureNewUserPwd = sureNewUserPwd;
        return this;
    }
}
