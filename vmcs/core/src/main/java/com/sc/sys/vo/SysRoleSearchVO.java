package com.sc.sys.vo;

/**
 * what:   系统角色查询
 *
 * @author 孙超 created on 2018/11/8
 */
public class SysRoleSearchVO {
    private Integer id;
    private String roleCode;
    private Integer roleStatus;

    @Override
    public String toString() {
        return "SysRoleVO{" +
                "id=" + id +
                ", roleCode='" + roleCode + '\'' +
                ", roleStatus=" + roleStatus +
                '}';
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
