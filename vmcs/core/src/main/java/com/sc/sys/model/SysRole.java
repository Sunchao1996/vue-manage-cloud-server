package com.sc.sys.model;

import com.sc.sys.dao.SysRoleDao;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.Date;

/**
 * what:  系统角色
 *
 * @author 孙超 created on 2018/11/8
 */
public class SysRole implements Serializable{
    private Integer id;
    private String roleName;
    @NotBlank
    private String roleCode;
    private Integer roleStatus;
    private Date createTime;
    private String resourcesIds;

    @Override
    public String toString() {
        return "SysRole{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", roleCode='" + roleCode + '\'' +
                ", roleStatus=" + roleStatus +
                ", createTime=" + createTime +
                ", resourcesIds='" + resourcesIds + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public SysRole setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getRoleName() {
        return roleName;
    }

    public SysRole setRoleName(String roleName) {
        this.roleName = roleName;
        return this;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public SysRole setRoleCode(String roleCode) {
        this.roleCode = roleCode;
        return this;
    }

    public Integer getRoleStatus() {
        return roleStatus;
    }

    public SysRole setRoleStatus(Integer roleStatus) {
        this.roleStatus = roleStatus;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public SysRole setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getResourcesIds() {
        return resourcesIds;
    }

    public SysRole setResourcesIds(String resourcesIds) {
        this.resourcesIds = resourcesIds;
        return this;
    }
}
