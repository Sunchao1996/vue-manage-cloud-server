package com.sc.sys.vo;

import com.sc.util.page.PageSearchVO;

/**
 * what:   系统角色查询
 *
 * @author 孙超 created on 2018/11/8
 */
public class SysRoleSearchVO extends PageSearchVO {
    private Integer id;
    private String roleCode;
    private Integer roleStatus;
    private String roleName;

    public String getRoleNameLike() {
        return "%" + this.roleName + "%";
    }

    @Override
    public String toString() {
        return "SysRoleSearchVO{" +
                "id=" + id +
                ", roleCode='" + roleCode + '\'' +
                ", roleStatus=" + roleStatus +
                ", roleName='" + roleName + '\'' +
                '}';
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getId() {
        return id;
    }

    public SysRoleSearchVO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public SysRoleSearchVO setRoleCode(String roleCode) {
        this.roleCode = roleCode;
        return this;
    }

    public Integer getRoleStatus() {
        return roleStatus;
    }

    public SysRoleSearchVO setRoleStatus(Integer roleStatus) {
        this.roleStatus = roleStatus;
        return this;
    }
}
