package com.sc.sys.model;

/**
 * what:  角色资源关联
 *
 * @author 孙超 created on 2018/11/8
 */
public class SysRolesResources {
    private Integer id;
    private Integer roleId;
    private Integer resourceId;

    @Override
    public String toString() {
        return "SysRolesResources{" +
                "id=" + id +
                ", roleId=" + roleId +
                ", resourceId=" + resourceId +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public SysRolesResources setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public SysRolesResources setRoleId(Integer roleId) {
        this.roleId = roleId;
        return this;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public SysRolesResources setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
        return this;
    }
}
