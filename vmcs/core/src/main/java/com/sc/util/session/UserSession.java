package com.sc.util.session;

import java.io.Serializable;

/**
 * 系统用户Session信息 该类可以根据实际信息进行修改
 *
 * @author 王晓安
 */
public class UserSession implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private int userId;

    /**
     * 用户IP
     */
    private String userIp;

    /**
     * 用户名  即登录账号
     */
    private String username;

    /**
     * 真实姓名
     */
    private String realname;

    /**
     * 角色id
     */
    private int roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 头像地址
     */
    private String avatar;


    @Override
    public String toString() {
        return "UserSession{" +
                "userId=" + userId +
                ", userIp='" + userIp + '\'' +
                ", username='" + username + '\'' +
                ", realname='" + realname + '\'' +
                ", roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
