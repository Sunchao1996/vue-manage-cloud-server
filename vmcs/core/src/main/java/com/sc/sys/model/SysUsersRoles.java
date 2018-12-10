package com.sc.sys.model;

/**
 * what:  用户角色关系
 *
 * @author 孙超 created on 2018/11/8
 */
public class SysUsersRoles {
    private Integer id;
    private Integer userId;
    private Integer roleId;

    @Override
    public String toString() {
        return "SysUsersRoles{" +
                "id=" + id +
                ", userId=" + userId +
                ", roleId=" + roleId +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public SysUsersRoles setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getUserId() {
        return userId;
    }

    public SysUsersRoles setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public SysUsersRoles setRoleId(Integer roleId) {
        this.roleId = roleId;
        return this;
    }
}
